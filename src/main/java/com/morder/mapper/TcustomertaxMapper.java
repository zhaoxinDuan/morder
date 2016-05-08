package com.morder.mapper;

import com.morder.model.Tcustomertax;

public interface TcustomertaxMapper {
    int deleteByPrimaryKey(Integer idtcustax);

    int insert(Tcustomertax record);

    int insertSelective(Tcustomertax record);

    Tcustomertax selectByPrimaryKey(Integer idtcustax);

    int updateByPrimaryKeySelective(Tcustomertax record);

    int updateByPrimaryKey(Tcustomertax record);
}