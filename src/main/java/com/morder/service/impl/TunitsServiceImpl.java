package com.morder.service.impl;

import com.morder.mapper.TunitsMapper;
import com.morder.model.Tunits;
import com.morder.service.TunitsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class TunitsServiceImpl implements TunitsService {
    private static final Logger logger = LoggerFactory.getLogger(TunitsServiceImpl.class);
    @Autowired
    private TunitsMapper tunitsMapper;

    public Integer save(Tunits record) {
        Integer count = null;
        if(record.getIdunit()==null){
            count = this.tunitsMapper.insert(record);
        }else{
            count = this.tunitsMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Tunits record) {
        Integer count = null;
        if(record.getIdunit()==null){
            count = this.tunitsMapper.insertSelective(record);
        }else{
            count = this.tunitsMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idunit) {
        return this.tunitsMapper.deleteByPrimaryKey(idunit);
    }

    public Tunits selectByPrimaryKey(Integer idunit) {
        return this.tunitsMapper.selectByPrimaryKey(idunit);
    }

    public List<Tunits> findAllUnits() {
        return this.tunitsMapper.findAllUnits();
    }
}
