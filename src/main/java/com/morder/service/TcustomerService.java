package com.morder.service;

import com.morder.model.Tcustomer;

import java.util.List;

/**
 * Created by amis on 16-5-15.
 */
public interface TcustomerService {
    Integer save(Tcustomer record);

    Integer saveSelective(Tcustomer record);

    void deleteByPrimaryKey(Integer idcustomer,Integer idcusapp,Integer idtcustax);

    Tcustomer selectByPrimaryKey(Integer idcustomer);

    List findAllCustomers(Integer start,Integer limit);

    List findAllCustomersNolimit();
}
