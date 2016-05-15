package com.morder.service;

import com.morder.model.Bmorder;

/**
 * Created by amis on 16-5-15.
 */
public interface BmorderService {

    Integer save(Bmorder record);

    Integer saveSelective(Bmorder record);

    Integer deleteByPrimaryKey(Integer idbmorder);

    Bmorder selectByPrimaryKey(Integer idbmorder);
}
