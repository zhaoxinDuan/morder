package com.morder.service;

import com.github.pagehelper.PageInfo;
import com.morder.model.Tuser;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
public interface TuserService {
    Integer save(Tuser record, Integer idunit);

    Integer saveSelective(Tuser record, Integer idunit);

    Integer deleteByPrimaryKey(Integer iduser);

    Tuser selectByPrimaryKey(Integer iduser);

    PageInfo findAllUsers(Integer start, Integer limit);

    List findAllUsersNoLimit();

    PageInfo findAllUnitUsers(Integer start, Integer limit, Integer idunit);

    Integer userUpdateStatus(Integer iduser, Integer uisdel);

    Tuser selectByUname(String uname);
}
