package com.morder.service;

import com.morder.model.Tunits;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
public interface TunitsService {
    Integer save(Tunits record);

    Integer saveSelective(Tunits record);

    Integer deleteByPrimaryKey(Integer idunit);

    Tunits selectByPrimaryKey(Integer idunit);

    List<Tunits> findAllUnits();
}
