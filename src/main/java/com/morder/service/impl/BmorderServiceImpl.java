package com.morder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.morder.mapper.*;
import com.morder.model.*;
import com.morder.service.BmorderService;
import com.morder.service.TcustomerService;
import com.morder.utils.NumUtil;
import com.morder.utils.Utils;
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

    public Integer updateStatus(Bmorder record) {
        return this.bmorderMapper.updateByPrimaryKeySelective(record);
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
            record.setBmorderamount(new BigDecimal(0));
            count = this.bmorderMapper.insertSelective(record);
            Bmorder bmorder = new Bmorder();
            bmorder.setIdbmorder(record.getIdbmorder());
            bmorder.setBmordernum(createOrderNum());
            this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
            record.setBmordernum(bmorder.getBmordernum());
        } else {
            record.setBmorderamount(null);
            count = this.bmorderMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    private synchronized String createOrderNum() {

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
        return "YW"+ordernum;
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
        Bmorder instance = this.bmorderMapper.selectByPrimaryKey(record.getBmorderIdbmorder());

        if (record.getIdbmitem() == null) {
            bmorder.setBmorderamount(instance.getBmorderamount().add(record.getBmiamount()));
            count = this.bmorderitemMapper.insertSelective(record);
        } else {
            Bmorderitem record1 = this.bmorderitemMapper.selectByPrimaryKey(record.getIdbmitem());
            bmorder.setBmorderamount(instance.getBmorderamount().subtract(record1.getBmiamount()).add(record.getBmiamount()));
            count = this.bmorderitemMapper.updateByPrimaryKeySelective(record);
        }
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
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
        Bmorder instance = this.bmorderMapper.selectByPrimaryKey(record.getBmorderIdbmorder());


        if (record.getIdbmaddcosts() == null) {
            bmorder.setBmorderamount(instance.getBmorderamount().add(record.getBmcosts()));
            count = this.bmaddcostsMapper.insertSelective(record);
        } else {
            Bmaddcosts record1 = this.bmaddcostsMapper.selectByPrimaryKey(record.getIdbmaddcosts());
            bmorder.setBmorderamount(instance.getBmorderamount().subtract(record1.getBmcosts()).add(record.getBmcosts()));
            count = this.bmaddcostsMapper.updateByPrimaryKeySelective(record);
        }
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        return count;
    }

    public Bmaddcosts selectCostByPrimaryKey(Integer idbmaddcosts) {
        return this.bmaddcostsMapper.selectByPrimaryKey(idbmaddcosts);
    }

    public Integer deleteCostmByPrimaryKey(Integer idbmaddcosts, Bmorder bmorder) {
        this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
        return this.bmaddcostsMapper.deleteByPrimaryKey(idbmaddcosts);
    }

    public List<Bmaddcosts> findCostsByIdbmorder(Integer idbmorder) {
        return this.bmaddcostsMapper.findCostsByIdbmorder(idbmorder);
    }

    public Integer copyOrder(Integer idbmorder) {
        Bmorder bmorder = this.bmorderMapper.selectByPrimaryKey(idbmorder);
        List<Bmaddcosts> bmaddcostses = this.bmaddcostsMapper.findCostsByIdbmorder(idbmorder);
        List<Bmorderitem> bmorderitems = this.bmorderitemMapper.findItemsByIdbmorder(idbmorder);
        bmorder.setIdbmorder(null);
        bmorder.setBmordernum(null);
        bmorder.setBmdenum(null);
        bmorder.setBmordernum(createOrderNum());
        this.bmorderMapper.insertSelective(bmorder);
        for(Bmorderitem bmorderitem:bmorderitems){
            bmorderitem.setIdbmitem(null);
            bmorderitem.setBmorderIdbmorder(bmorder.getIdbmorder());
            this.bmorderitemMapper.insertSelective(bmorderitem);
        }
        for(Bmaddcosts bmaddcosts:bmaddcostses){
            bmaddcosts.setIdbmaddcosts(null);
            bmaddcosts.setBmorderIdbmorder(bmorder.getIdbmorder());
            this.bmaddcostsMapper.insertSelective(bmaddcosts);
        }

        return bmorder.getIdbmorder();

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
                if(!isOnlyUpdateMorder) {
                    bmorder.setBmdenum(String.valueOf(bmmarker.getBmmtype() + bmmarker.getBmmnum()));
                }else{
                    bmorder.setBmdenum(String.valueOf(bmmarker.getBmmnum()));
                }
                this.bmorderMapper.updateByPrimaryKeySelective(bmorder);
            }
        }
        if(!isOnlyUpdateMorder) {
            if (bmmarker.getIdbmmarker() == null) {
                this.bmmarkerMapper.insertSelective(bmmarker);
            } else {
                this.bmmarkerMapper.updateByPrimaryKeySelective(bmmarker);
            }
        }
    }
}
