package com.morder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.morder.mapper.*;
import com.morder.model.*;
import com.morder.service.BmorderService;
import com.morder.service.TcustomerService;
import com.morder.utils.NumUtil;
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
    @Autowired
    private TcustomerService tcustomerService;
    @Autowired
    private BmaddcostsMapper bmaddcostsMapper;
    @Autowired
    private BmmarkerMapper bmmarkerMapper;

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
        if (record.getTcustomerIdcustomer() == null) {
            Tcustomer tcustomer = new Tcustomer();
            tcustomer.setCname(record.getBmcusname());
            Tcustomerappend tcustomerappend = new Tcustomerappend();
            tcustomerappend.setCacontacts("");
            tcustomerappend.setCamphone("");
            tcustomer.setTcustomerappend(tcustomerappend);
            Tcustomertax tcustomertax = new Tcustomertax();
            tcustomertax.setTcbankname("");
            tcustomer.setTcustomertax(tcustomertax);
            this.tcustomerService.saveSelective(tcustomer);
            record.setTcustomerIdcustomer(tcustomer.getIdcustomer());
        }
        if (record.getIdbmorder() == null) {
            count = this.bmorderMapper.insertSelective(record);
            Bmorder bmorder = new Bmorder();
            bmorder.setIdbmorder(record.getIdbmorder());
            synchronized (this) {
                Integer bmmtype = NumUtil.getCurrOrderType();
                Bmmarker bmmarker = this.bmmarkerMapper.selectByBmmtype(bmmtype);
                String ordernum = null;
                if (bmmarker == null) {
                    bmmarker = new Bmmarker();
                    bmmarker.setBmmnum(1);
                    bmmarker.setBmmtype(bmmtype);
                    this.bmmarkerMapper.insertSelective(bmmarker);
                    ordernum = String.valueOf(bmmtype + 1);
                } else {
                    bmmarker.setBmmnum(bmmarker.getBmmnum() + 1);
                    ordernum = String.valueOf(bmmtype + bmmarker.getBmmnum());
                    this.bmmarkerMapper.updateByPrimaryKeySelective(bmmarker);
                }
                bmorder.setBmordernum(ordernum);
            }

            this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
            record.setBmordernum(bmorder.getBmordernum());
        } else {
            count = this.bmorderMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idbmorder) {
        this.bmaddcostsMapper.deleteCostsByIdbmorder(idbmorder);
        this.bmorderitemMapper.deleteItemsByIdbmorder(idbmorder);
        return this.bmorderMapper.deleteByPrimaryKey(idbmorder);
    }

    public Bmorder selectByPrimaryKey(Integer idbmorder) {
        return this.bmorderMapper.selectByPrimaryKey(idbmorder);
    }

    public PageInfo findAllBmorders(Integer start, Integer limit,String filters) {

        PageHelper.startPage(start, limit);
        List list = this.bmorderMapper.findAllBmordersByPage(filters);
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


    public Integer saveCostSelective(Bmaddcosts record, BigDecimal changebmorderamount) {
        Integer count = null;
        Bmorder bmorder = new Bmorder();
        bmorder.setIdbmorder(record.getBmorderIdbmorder());
        bmorder.setBmorderamount(changebmorderamount);
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        if (record.getIdbmaddcosts() == null) {
            count = this.bmaddcostsMapper.insertSelective(record);
        } else {
            count = this.bmaddcostsMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Bmaddcosts selectCostByPrimaryKey(Integer idbmaddcosts) {
        return this.bmaddcostsMapper.selectByPrimaryKey(idbmaddcosts);
    }

    public Integer deleteCostmByPrimaryKey(Integer idbmaddcosts, Bmorder bmorder) {
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        return this.bmaddcostsMapper.deleteByPrimaryKey(idbmaddcosts);
    }

    public List findCostsByIdbmorder(Integer idbmorder) {
        return this.bmaddcostsMapper.findCostsByIdbmorder(idbmorder);
    }

    public PageInfo findAllBmCosts(Integer start, Integer limit) {
        PageHelper.startPage(start, limit);
        List list = this.bmaddcostsMapper.findAllBmCostsByPage();
        PageInfo page = new PageInfo(list);
        return page;
    }



    public List<Map> findAllBmordersByMorderid(Integer idbmorder) {
        return this.bmorderMapper.findAllBmordersByMorderid(idbmorder);
    }

    public List<Map> findBmorderAndItemByMorderid(Integer idbmorder) {
        return this.bmorderMapper.findBmorderAndItemByMorderid(idbmorder);
    }

    public void saveBmNum(List<Integer> idbmorderls,Bmmarker bmmarker,Boolean isOnlyUpdateMorder){
        if(idbmorderls!=null&&idbmorderls.size()>0){
            Bmorder bmorder = null;
            for(Integer idbmorder:idbmorderls){
                bmorder = new Bmorder();
                bmorder.setIdbmorder(idbmorder);
                bmorder.setBmdenum(String.valueOf(bmmarker.getBmmnum()));
                this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
            }
        }
        if(isOnlyUpdateMorder) {
            if (bmmarker.getIdbmmarker() == null) {
                this.bmmarkerMapper.insertSelective(bmmarker);
            } else {
                this.bmmarkerMapper.updateByPrimaryKeySelective(bmmarker);
            }
        }
    }
}
