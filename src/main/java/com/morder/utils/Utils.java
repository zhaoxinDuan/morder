package com.morder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by amis on 16-5-29.
 */
public class Utils {
    public static String getObjectToString(Object obj) {
        if (obj == null) return "";
        return String.valueOf(obj);
    }

    public static Integer getObjectToInteger(Object obj) {
        if (obj == null) return null;
        return Integer.parseInt(String.valueOf(obj));
    }

    public static String getDataObjectToStringCN(Object obj) {
        if (obj == null) return "";
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format((Date) obj);
    }

    public static String convertToChineseNumberNew(double number) throws Exception {
        StringBuffer chineseNumber = new StringBuffer();
        String[] num = {" 零 ", " 壹 ", " 贰 ", " 叁 ", " 肆 ", " 伍 ", " 陆 ", " 柒 ", " 捌 ", " 玖 "};
        String[] unit = {"分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"};
        String tempNumber = String.valueOf(Math.round((number * 100)));
        int tempNumberLength = tempNumber.length();
        for (int i = tempNumberLength; i < 8; i++) {
            tempNumber = "0" + tempNumber;
        }
        tempNumberLength = tempNumber.length();
//        10000000
        for (int i = 0; i < tempNumberLength; i++) {
            chineseNumber.append(num[Integer.parseInt(tempNumber.substring(i, i + 1))]);
            chineseNumber.append(unit[tempNumberLength - 1 - i]);
        }

        return chineseNumber.toString();
    }

    /*
    * 将小写的人民币转化成大写
    */
    public static String convertToChineseNumber(double number) throws Exception {
        StringBuffer chineseNumber = new StringBuffer();
        String[] num = {" 零 ", " 壹 ", " 贰 ", " 叁 ", " 肆 ", " 伍 ", " 陆 ", " 柒 ", " 捌 ", " 玖 "};
        String[] unit = {"分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"};
        String tempNumber = String.valueOf(Math.round((number * 100)));
        int tempNumberLength = tempNumber.length();
        if ("0".equals(tempNumber)) {
            return " 零圆整";
        }
        if (tempNumberLength > 15) {
            throw new Exception("超出转化范围.");
        }
        boolean preReadZero = true;    //前面的字符是否读零
        for (int i = tempNumberLength; i > 0; i--) {
            if ((tempNumberLength - i + 2) % 4 == 0) {
                //如果在（圆，万，亿，万）位上的四个数都为零,如果标志为未读零，则只读零，如果标志为已读零，则略过这四位
                if (i - 4 >= 0 && "0000".equals(tempNumber.substring(i - 4, i))) {
                    if (!preReadZero) {
                        chineseNumber.insert(0, "零");
                        preReadZero = true;
                    }
                    i -= 3;    //下面还有一个i--
                    continue;
                }
                //如果当前位在（圆，万，亿，万）位上，则设置标志为已读零（即重置读零标志）
                preReadZero = true;
            }
            Integer digit = Integer.parseInt(tempNumber.substring(i - 1, i));
            if (digit == 0) {
                //如果当前位是零并且标志为未读零，则读零，并设置标志为已读零
                if (!preReadZero) {
                    chineseNumber.insert(0, "零");
                    preReadZero = true;
                }
                //如果当前位是零并且在（圆，万，亿，万）位上，则读出（圆，万，亿，万）
                if ((tempNumberLength - i + 2) % 4 == 0) {
                    chineseNumber.insert(0, unit[tempNumberLength - i]);
                }
            }
            //如果当前位不为零，则读出此位，并且设置标志为未读零
            else {
                chineseNumber.insert(0, num[digit] + unit[tempNumberLength - i]);
                preReadZero = false;
            }
        }
        //如果分角两位上的值都为零，则添加一个“整”字
        if (tempNumberLength - 2 >= 0 && "00".equals(tempNumber.substring(tempNumberLength - 2, tempNumberLength))) {
            chineseNumber.append("整");
        }
        return chineseNumber.toString();
    }


    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if(i!=0){
                sb.append(separator);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println("convertToChineseNumber(123455.22) = " + convertToChineseNumberNew(455.00));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
