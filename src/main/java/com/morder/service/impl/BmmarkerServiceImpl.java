package com.morder.service.impl;

import com.morder.mapper.BmmarkerMapper;
import com.morder.model.Bmmarker;
import com.morder.service.BmmarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amis on 16-6-5.
 */
@Service
public class BmmarkerServiceImpl implements BmmarkerService {
    @Autowired
    private BmmarkerMapper bmmarkerMapper;

    public Bmmarker selectByBmmtype(Integer bmmtype) {
        return this.bmmarkerMapper.selectByBmmtype(bmmtype);
    }

    public Integer saveBmmarker(Bmmarker record) {
        Integer count = null;
        if (record.getIdbmmarker() != null) {
            count = this.bmmarkerMapper.updateByPrimaryKeySelective(record);
        } else {
            count = this.bmmarkerMapper.insertSelective(record);
        }
        return count;
    }
}
