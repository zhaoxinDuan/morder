package com.morder.service;

import com.morder.model.Tuser;

/**
 * Created by amis on 16-5-15.
 */
public interface TuserService {
    Integer save(Tuser record);

    Integer saveSelective(Tuser record);

    Integer deleteByPrimaryKey(Integer iduser);

    Tuser selectByPrimaryKey(Integer iduser);
}
