package com.morder.mapper;

import com.morder.model.Tuser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer iduser);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Integer iduser);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);

    List findAllUsersByPage(RowBounds rowBounds);

    List findAllUsersNoLimit();

    List findAllUnitUsersByPage(RowBounds rowBounds,@Param("idunit")Integer idunit);

    int userUpdateStatus(@Param("iduser")Integer iduser,@Param("uisdel")Integer uisdel);

    int userUpdatePwd(@Param("iduser")Integer iduser,@Param("upwd")Integer upwd);

    Tuser selectByUname(@Param("uname")String uname);
}