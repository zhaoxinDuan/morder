package com.morder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amis on 16-5-29.
 */
public class Utils {
    public static String getObjectToString(Object obj){
        if(obj==null) return "";
        return String.valueOf(obj);
    }
    public static Integer getObjectToInteger(Object obj){
        if(obj==null) return null;
        return Integer.parseInt(String.valueOf(obj));
    }

    public static String getDataObjectToStringCN(Object obj){
        if(obj==null) return "";
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format((Date)obj);
    }

}
