package com.morder.utils;

import java.util.Calendar;

/**
 * Created by amis on 16-5-24.
 */
public class NumUtil {

    public static Integer getCurrOrderType(){
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH)+1;
        return Integer.parseInt(String.valueOf(year).substring(2))*1000000+(month*10000);
    }


    public static Integer getCurrDeType(){
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);
        return Integer.parseInt(String.valueOf(year).substring(2))*100000;
    }

}
