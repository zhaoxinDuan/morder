package com.morder.mapper;

import com.morder.model.Tlogs;

public interface TlogsMapper {
    int deleteByPrimaryKey(Integer tlogsid);

    int insert(Tlogs record);

    int insertSelective(Tlogs record);

    Tlogs selectByPrimaryKey(Integer tlogsid);

    int updateByPrimaryKeySelective(Tlogs record);

    int updateByPrimaryKey(Tlogs record);
}