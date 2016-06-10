package com.morder.mapper;

import com.morder.model.Trelunit;

public interface TrelunitMapper {
    int insert(Trelunit record);

    int insertSelective(Trelunit record);

    int deleteByUserid(Integer tuserIduser);
}