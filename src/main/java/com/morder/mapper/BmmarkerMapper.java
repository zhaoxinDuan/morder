package com.morder.mapper;

import com.morder.model.Bmmarker;

public interface BmmarkerMapper {
    int deleteByPrimaryKey(Integer idbmmarker);

    int insert(Bmmarker record);

    int insertSelective(Bmmarker record);

    Bmmarker selectByPrimaryKey(Integer idbmmarker);


    Bmmarker selectByBmmtype(Integer bmmtype);

    int updateByPrimaryKeySelective(Bmmarker record);

    int updateByPrimaryKey(Bmmarker record);
}