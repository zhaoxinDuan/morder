package com.morder.service;

import com.morder.model.Tcustomer;

/**
 * Created by amis on 16-5-15.
 */
public interface TcustomerService {
    Integer save(Tcustomer record);

    Integer saveSelective(Tcustomer record);

    Integer deleteByPrimaryKey(Integer idcustomer);

    Tcustomer selectByPrimaryKey(Integer idcustomer);
}
