package com.morder.service;

import com.github.pagehelper.PageInfo;
import com.morder.model.Bmaddcosts;
import com.morder.model.Bmmarker;
import com.morder.model.Bmorder;
import com.morder.model.Bmorderitem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by amis on 16-5-15.
 */
public interface BmorderService {

    Integer save(Bmorder record);

    Integer saveSelective(Bmorder record);

    Integer updateStatus(Bmorder record);

    Integer deleteByPrimaryKey(Integer idbmorder);

    Bmorder selectByPrimaryKey(Integer idbmorder);

    PageInfo findAllBmorders(Integer start,Integer limit,String filters);

    PageInfo findAllBmordersByDetails(Integer start,Integer limit,String filters);

    BigDecimal selectSumBmorderamount(String filters);

    Integer selectBmorderCount(String filters);


    Integer saveItemSelective(Bmorderitem record,BigDecimal changebmorderamount);

    Bmorderitem selectItemByPrimaryKey(Integer idbmitem);

    Integer deleteItemByPrimaryKey(Integer idbmitem,Bmorder bmorder);

    List findItemsByIdbmorder(Integer idbmorder);

    PageInfo findAllBmorderitems(Integer start,Integer limit);


    Integer saveCostSelective(Bmaddcosts record,BigDecimal changebmorderamount);

    Bmaddcosts selectCostByPrimaryKey(Integer idbmaddcosts);

    Integer deleteCostmByPrimaryKey(Integer idbmaddcosts,Bmorder bmorder);

    List<Bmaddcosts> findCostsByIdbmorder(Integer idbmorder);

    PageInfo findAllBmCosts(Integer start,Integer limit);



    List<Map> findAllBmordersByMorderid(Integer idbmorder);

    List<Map> findBmorderAndItemByMorderid(Integer idbmorder);

    void saveBmNum(List<Integer> idbmorderls,Bmmarker bmmarker,Boolean isOnlyUpdateMorder);
}
