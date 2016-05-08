package com.morder.mapper;

import com.morder.model.Tuser;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer iduser);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Integer iduser);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);
}