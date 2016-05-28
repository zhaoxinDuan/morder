package com.morder.service;

import com.morder.model.Bmorder;
import com.morder.model.Bmorderitem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
public interface BmorderService {

    Integer save(Bmorder record);

    Integer saveSelective(Bmorder record);

    Integer deleteByPrimaryKey(Integer idbmorder);

    Bmorder selectByPrimaryKey(Integer idbmorder);

    List findAllBmorders(Integer start,Integer limit);

    Integer saveItemSelective(Bmorderitem record,BigDecimal changebmorderamount);

    Bmorderitem selectItemByPrimaryKey(Integer idbmitem);

    Integer deleteItemByPrimaryKey(Integer idbmitem,Bmorder bmorder);

    List findItemsByIdbmorder(Integer idbmorder);

    List findAllBmorderitems(Integer start,Integer limit);

}
