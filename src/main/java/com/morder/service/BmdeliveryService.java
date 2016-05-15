package com.morder.service;

import com.morder.model.Bmdelivery;

/**
 * Created by amis on 16-5-15.
 */
public interface BmdeliveryService {

    Integer save(Bmdelivery record);

    Integer saveSelective(Bmdelivery record);

    Integer deleteByPrimaryKey(Integer idbmdelivery);

    Bmdelivery selectByPrimaryKey(Integer idbmdelivery);
}
