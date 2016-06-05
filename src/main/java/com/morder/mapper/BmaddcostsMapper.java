package com.morder.mapper;

import com.morder.model.Bmaddcosts;

import java.util.List;

public interface BmaddcostsMapper {
    int deleteByPrimaryKey(Integer idbmaddcosts);

    int insert(Bmaddcosts record);

    int insertSelective(Bmaddcosts record);

    Bmaddcosts selectByPrimaryKey(Integer idbmaddcosts);

    int updateByPrimaryKeySelective(Bmaddcosts record);

    int updateByPrimaryKey(Bmaddcosts record);

    List findCostsByIdbmorder(Integer bmorderIdbmorder);

    int deleteCostsByIdbmorder(Integer bmorderIdbmorder);

    List findAllBmCostsByPage();


}