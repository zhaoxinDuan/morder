package com.morder.service.impl;

import com.morder.mapper.TrelunitMapper;
import com.morder.mapper.TuserMapper;
import com.morder.model.Trelunit;
import com.morder.model.Tuser;
import com.morder.service.TuserService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class TuserServiceImpl implements TuserService {
    private static final Logger logger = LoggerFactory.getLogger(TuserServiceImpl.class);
    @Autowired
    private TuserMapper tuserMapper;
    @Autowired
    private TrelunitMapper trelunitMapper;

    public Integer save(Tuser record,Integer idunit) {
        Integer count = null;
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        if(record.getIduser()==null){
            record.setCreateTime(new Date(System.currentTimeMillis()));
            record.setUpwd(md5PasswordEncoder.encodePassword(StringUtils.isEmpty(record.getUpwd())?"123456":record.getUpwd(),null));
            record.setUisdel(0);
            count = this.tuserMapper.insert(record);
            Trelunit trelunit = new Trelunit();
            trelunit.setTunitsIdunit(idunit);
            trelunit.setTuserIduser(record.getIduser());
            trelunitMapper.insert(trelunit);
        }else{
            record.setUpwd(md5PasswordEncoder.encodePassword(StringUtils.isEmpty(record.getUpwd())?null:record.getUpwd(),null));
            count = this.tuserMapper.insertSelective(record);
        }
        return count;
    }

    public Integer saveSelective(Tuser record,Integer idunit) {
        Integer count = null;
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        if(record.getIduser()==null){
            record.setCreateTime(new Date(System.currentTimeMillis()));
            record.setUpwd(md5PasswordEncoder.encodePassword(StringUtils.isEmpty(record.getUpwd())?"123456":record.getUpwd(),null));
            record.setUisdel(0);
            count = this.tuserMapper.insertSelective(record);
            Trelunit trelunit = new Trelunit();
            trelunit.setTunitsIdunit(idunit);
            trelunit.setTuserIduser(record.getIduser());
            trelunitMapper.insert(trelunit);

        }else{
            record.setUpwd(md5PasswordEncoder.encodePassword(StringUtils.isEmpty(record.getUpwd())?null:record.getUpwd(),null));
            count = this.tuserMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer iduser) {
        return this.tuserMapper.deleteByPrimaryKey(iduser);
    }

    public Tuser selectByPrimaryKey(Integer iduser) {
        return this.tuserMapper.selectByPrimaryKey(iduser);
    }

    public List findAllUsers(Integer start, Integer limit) {
        RowBounds rowBounds = new RowBounds(start,limit);
        return this.tuserMapper.findAllUsersByPage(rowBounds);
    }

    public List findAllUsersNoLimit() {
        return this.tuserMapper.findAllUsersNoLimit();
    }

    public List findAllUnitUsers(Integer start, Integer limit, Integer idunit) {
        return this.tuserMapper.findAllUnitUsersByPage(new RowBounds(start,limit),idunit);
    }

    public Integer userUpdateStatus(Integer iduser,Integer uisdel) {
        return this.tuserMapper.userUpdateStatus(iduser, uisdel);
    }

    public Tuser selectByUname(String uname) {
        return this.tuserMapper.selectByUname(uname);
    }
}
