package com.morder.utils;

/**
 * Created by amis on 16-5-24.
 */
public class OrderNumUtil {
    private static final String ONPRX="YW";
    private static final Integer ONNUM=8000000;
    public static String createOrderNum(Integer id){
        return ONPRX+(ONNUM+id);
    }
}
