package com.morder.mapper;

import com.morder.model.Tunits;

import java.util.List;

public interface TunitsMapper {
    int deleteByPrimaryKey(Integer idunit);

    int insert(Tunits record);

    int insertSelective(Tunits record);

    Tunits selectByPrimaryKey(Integer idunit);

    int updateByPrimaryKeySelective(Tunits record);

    int updateByPrimaryKey(Tunits record);

    List<Tunits> findAllUnits();
}