package com.morder.service.impl;

import com.morder.mapper.TcustomerMapper;
import com.morder.model.Tcustomer;
import com.morder.service.TcustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class TcustomerServiceImpl implements TcustomerService {
    private static final Logger logger = LoggerFactory.getLogger(TcustomerServiceImpl.class);
    @Autowired
    private TcustomerMapper tcustomerMapper;

    public Integer save(Tcustomer record) {
        Integer count = null;
        if(record.getIdcustomer()==null){
            count = this.tcustomerMapper.insert(record);
        }else{
            count = this.tcustomerMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Tcustomer record) {
        Integer count = null;
        if(record.getIdcustomer()==null){
            count = this.tcustomerMapper.insertSelective(record);
        }else{
            count = this.tcustomerMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idcustomer) {
        return this.tcustomerMapper.deleteByPrimaryKey(idcustomer);
    }

    public Tcustomer selectByPrimaryKey(Integer idcustomer) {
        return this.tcustomerMapper.selectByPrimaryKey(idcustomer);
    }
}
