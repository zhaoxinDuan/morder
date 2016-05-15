package com.morder.service.impl;

import com.morder.mapper.BmdeliveryMapper;
import com.morder.model.Bmdelivery;
import com.morder.service.BmdeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class BmdeliveryServiceImpl implements BmdeliveryService{
    private static final Logger logger = LoggerFactory.getLogger(BmdeliveryServiceImpl.class);

    @Autowired
    private BmdeliveryMapper bmdeliveryMapper;

    public Integer save(Bmdelivery record) {
        Integer count = null;
        if(record.getIdbmdelivery()==null){
            count = this.bmdeliveryMapper.insert(record);
        }else{
            count = this.bmdeliveryMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Bmdelivery record) {
        Integer count = null;
        if(record.getIdbmdelivery()==null){
            count = this.bmdeliveryMapper.insertSelective(record);
        }else{
            count = this.bmdeliveryMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idbmdelivery) {
        return this.bmdeliveryMapper.deleteByPrimaryKey(idbmdelivery);
    }

    public Bmdelivery selectByPrimaryKey(Integer idbmdelivery) {
        return this.bmdeliveryMapper.selectByPrimaryKey(idbmdelivery);
    }
}
