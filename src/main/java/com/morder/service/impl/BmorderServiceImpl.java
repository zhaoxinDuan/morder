package com.morder.service.impl;

import com.morder.mapper.BmorderMapper;
import com.morder.model.Bmorder;
import com.morder.service.BmorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amis on 16-5-15.
 */
@Service
public class BmorderServiceImpl implements BmorderService {
    private static final Logger logger = LoggerFactory.getLogger(BmorderServiceImpl.class);
    @Autowired
    private BmorderMapper bmorderMapper;

    public Integer save(Bmorder record) {
        Integer count = null;
        if(record.getIdbmorder()==null){
            count = this.bmorderMapper.insert(record);
        }else{
            count = this.bmorderMapper.updateByPrimaryKey(record);
        }
        return count;
    }

    public Integer saveSelective(Bmorder record) {
        Integer count = null;
        if(record.getIdbmorder()==null){
            count = this.bmorderMapper.insertSelective(record);
        }else{
            count = this.bmorderMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    public Integer deleteByPrimaryKey(Integer idbmorder) {
        return this.bmorderMapper.deleteByPrimaryKey(idbmorder);
    }

    public Bmorder selectByPrimaryKey(Integer idbmorder) {
        return this.bmorderMapper.selectByPrimaryKey(idbmorder);
    }
}
