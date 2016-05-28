package com.morder.mapper;

import com.morder.model.Bmorderitem;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BmorderitemMapper {
    int deleteByPrimaryKey(Integer idbmitem);

    int insert(Bmorderitem record);

    int insertSelective(Bmorderitem record);

    Bmorderitem selectByPrimaryKey(Integer idbmitem);

    int updateByPrimaryKeySelective(Bmorderitem record);

    int updateByPrimaryKey(Bmorderitem record);

    List findItemsByIdbmorder(Integer bmorderIdbmorder);

    int deleteItemsByIdbmorder(Integer bmorderIdbmorder);

    List findAllBmorderitemsByPage(RowBounds rowBounds);
}