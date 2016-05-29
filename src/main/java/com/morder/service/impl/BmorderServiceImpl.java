package com.morder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.morder.mapper.BmorderMapper;
import com.morder.mapper.BmorderitemMapper;
import com.morder.model.Bmorder;
import com.morder.model.Bmorderitem;
import com.morder.service.BmorderService;
import com.morder.utils.OrderNumUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class BmorderServiceImpl implements BmorderService {
    private static final Logger logger = LoggerFactory.getLogger(BmorderServiceImpl.class);
    @Autowired
    private BmorderMapper bmorderMapper;
    @Autowired
    private BmorderitemMapper bmorderitemMapper;

    public Integer save(Bmorder record) {
        Integer count = null;
        if (record.getIdbmorder() == null) {
            count = this.bmorderMapper.insert(record);
        } else {
            count = this.bmorderMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Bmorder record) {
        Integer count = null;
        if (record.getIdbmorder() == null) {
            count = this.bmorderMapper.insertSelective(record);
            Bmorder bmorder = new Bmorder();
            bmorder.setIdbmorder(record.getIdbmorder());
            bmorder.setBmordernum(OrderNumUtil.createOrderNum(record.getIdbmorder()));
            this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
            record.setBmordernum(bmorder.getBmordernum());
        } else {
            count = this.bmorderMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idbmorder) {
        this.bmorderitemMapper.deleteItemsByIdbmorder(idbmorder);
        return this.bmorderMapper.deleteByPrimaryKey(idbmorder);
    }

    public Bmorder selectByPrimaryKey(Integer idbmorder) {
        return this.bmorderMapper.selectByPrimaryKey(idbmorder);
    }

    public PageInfo findAllBmorders(Integer start, Integer limit) {

        PageHelper.startPage(start, limit);
        List list = this.bmorderMapper.findAllBmordersByPage();
        PageInfo page = new PageInfo(list);
        return page;
    }

    public PageInfo findAllBmordersByDetails(Integer start, Integer limit, String filters) {
        PageHelper.startPage(start, limit);
        List list = this.bmorderMapper.findAllBmordersByDetails(filters);
        PageInfo page = new PageInfo(list);
        return page;
    }

    public BigDecimal selectSumBmorderamount(String filters) {
        return this.bmorderMapper.selectSumBmorderamount(filters);
    }

    public Integer selectBmorderCount(String filters) {
        return this.bmorderMapper.selectBmorderCount(filters);
    }
    public Integer saveItemSelective(Bmorderitem record, BigDecimal changebmorderamount) {
        Integer count = null;
        Bmorder bmorder = new Bmorder();
        bmorder.setIdbmorder(record.getBmorderIdbmorder());
        bmorder.setBmorderamount(changebmorderamount);
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        if (record.getIdbmitem() == null) {
            count = this.bmorderitemMapper.insertSelective(record);
        } else {
            count = this.bmorderitemMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteItemByPrimaryKey(Integer idbmitem, Bmorder bmorder) {
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        return this.bmorderitemMapper.deleteByPrimaryKey(idbmitem);
    }

    public Bmorderitem selectItemByPrimaryKey(Integer idbmitem) {
        return this.bmorderitemMapper.selectByPrimaryKey(idbmitem);
    }

    public List findItemsByIdbmorder(Integer idbmorder) {
        return this.bmorderitemMapper.findItemsByIdbmorder(idbmorder);
    }

    public PageInfo findAllBmorderitems(Integer start, Integer limit) {
        PageHelper.startPage(start, limit);
        List list = this.bmorderitemMapper.findAllBmorderitemsByPage();
        PageInfo page = new PageInfo(list);
        return page;
    }

    public Map findAllBmordersByItemid(Integer idbmitem) {
        return this.bmorderMapper.findAllBmordersByItemid(idbmitem);
    }
}
