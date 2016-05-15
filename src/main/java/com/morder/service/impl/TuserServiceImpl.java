package com.morder.service.impl;

import com.morder.mapper.TuserMapper;
import com.morder.model.Tuser;
import com.morder.service.TuserService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class TuserServiceImpl implements TuserService {
    private static final Logger logger = LoggerFactory.getLogger(TuserServiceImpl.class);
    @Autowired
    private TuserMapper tuserMapper;

    public Integer save(Tuser record) {
        Integer count = null;
        if(record.getIduser()==null){
            count = this.tuserMapper.insert(record);
        }else{
            count = this.tuserMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Tuser record) {
        Integer count = null;
        if(record.getIduser()==null){
            count = this.tuserMapper.insertSelective(record);
        }else{
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


}
