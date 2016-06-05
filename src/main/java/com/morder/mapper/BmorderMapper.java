package com.morder.mapper;

import com.morder.model.Bmorder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BmorderMapper {
    int deleteByPrimaryKey(Integer idbmorder);

    int insert(Bmorder record);

    int insertSelective(Bmorder record);

    Bmorder selectByPrimaryKey(Integer idbmorder);

    int updateByPrimaryKeySelective(Bmorder record);

    int updateByPrimaryKey(Bmorder record);

//    List findAllBmordersByPage(RowBounds rowBounds);
    List findAllBmordersByPage(@Param("filters")String fiters);

    List findAllBmordersByDetails(@Param("filters")String fiters);



    BigDecimal selectSumBmorderamount(@Param("filters")String fiters);

    Integer selectBmorderCount(@Param("filters")String fiters);


    List<Map> findAllBmordersByMorderid(@Param("idbmorder")Integer idbmorder);

    List<Map> findBmorderAndItemByMorderid(@Param("idbmorder")Integer idbmorder);
}