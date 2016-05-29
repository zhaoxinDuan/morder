package com.morder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.morder.mapper.TcustomerMapper;
import com.morder.mapper.TcustomerappendMapper;
import com.morder.mapper.TcustomertaxMapper;
import com.morder.model.Tcustomer;
import com.morder.model.Tcustomerappend;
import com.morder.model.Tcustomertax;
import com.morder.service.TcustomerService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class TcustomerServiceImpl implements TcustomerService {
    private static final Logger logger = LoggerFactory.getLogger(TcustomerServiceImpl.class);
    @Autowired
    private TcustomerMapper tcustomerMapper;
    @Autowired
    private TcustomerappendMapper tcustomerappendMapper;
    @Autowired
    private TcustomertaxMapper tcustomertaxMapper;

    public Integer save(Tcustomer record) {
        Integer count = null;
        if (record.getIdcustomer() == null) {
            count = this.tcustomerMapper.insert(record);
        } else {
            count = this.tcustomerMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Tcustomer record) {
        Integer count = null;
        if (record.getIdcustomer() == null) {
            Tcustomerappend tcustomerappend = record.getTcustomerappend();
            this.tcustomerappendMapper.insertSelective(tcustomerappend);
            Tcustomertax tcustomertax = record.getTcustomertax();
            this.tcustomertaxMapper.insertSelective(tcustomertax);
            record.setTcustomerappendIdcusapp(tcustomerappend.getIdcusapp());
            record.setTcustomertaxIdtcustax(tcustomertax.getIdtcustax());
            count = this.tcustomerMapper.insertSelective(record);
        } else {
            count = this.tcustomerMapper.updateByPrimaryKeySelective(record);
            this.tcustomerappendMapper.updateByPrimaryKeySelective(record.getTcustomerappend());
            this.tcustomertaxMapper.updateByPrimaryKeySelective(record.getTcustomertax());
        }
        return count;
    }

    public void deleteByPrimaryKey(Integer idcustomer, Integer idcusapp, Integer idtcustax) {
        this.tcustomerMapper.deleteByPrimaryKey(idcustomer);
        this.tcustomerappendMapper.deleteByPrimaryKey(idcusapp);
        this.tcustomertaxMapper.deleteByPrimaryKey(idtcustax);

    }

    public Tcustomer selectByPrimaryKey(Integer idcustomer) {
        return this.tcustomerMapper.selectByPrimaryKey(idcustomer);
    }

    public PageInfo findAllCustomers(Integer start, Integer limit) {
        PageHelper.startPage(start, limit);
        List list = this.tcustomerMapper.findAllCustomersByPage();
        PageInfo page = new PageInfo(list);
        return page;
    }

    public List findAllCustomersNolimit() {
        return this.tcustomerMapper.findAllCustomersNolimit();
    }
}
