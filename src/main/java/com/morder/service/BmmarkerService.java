package com.morder.service;

import com.morder.model.Bmmarker;

/**
 * Created by amis on 16-6-5.
 */
public interface BmmarkerService {
    Bmmarker selectByBmmtype(Integer bmmtype);

    Integer saveBmmarker(Bmmarker record);
}
