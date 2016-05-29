package com.morder.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amis on 16-5-29.
 */
public class ConstantUtils {
    public static Map protypeMap = new HashMap();
    public static Map statusMap = new HashMap();

    static {

        protypeMap.put(0,"折页");
        protypeMap.put(1,"切单张");
        protypeMap.put(2,"骑马钉");
        protypeMap.put(3,"锁线胶装");
        protypeMap.put(4,"精装");
        protypeMap.put(5,"YO装");

        statusMap.put(0,"未提交订单");
        statusMap.put(1,"已提交订单");
        statusMap.put(2,"已完成订单");


    }
}
