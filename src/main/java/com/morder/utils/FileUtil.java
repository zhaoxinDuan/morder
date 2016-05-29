package com.morder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by amis on 16-5-29.
 */
public class FileUtil {



    public static void copyFile(String url1, String url2) throws Exception {
        FileInputStream in = null;
        FileOutputStream out = null;
        byte[] buff = new byte[2048];
        File toFile = new File(url2);
//        if(!toFile.getParentFile().exists())toFile.getParentFile().mkdirs();
        if(!toFile.exists()){
            toFile.createNewFile();
        }
        try {
            in = new FileInputStream(new File(url1));
            out = new FileOutputStream(new File(url2));
            int n = 0;
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=in)in.close();
            if(null!=out)out.close();
        }
    }

    public static void main(String[] args){
        String url1="/home/amis/project/morder/src/main/webapp/resources/template/ptemplate.xls";
        String url2="/home/amis/project/morder/src/main/webapp/resources/template/temp/22.xls";
        try {
            copyFile(url1,url2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
