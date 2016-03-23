package com.example.mdw.bmobdemo.utils;

/**
 * Created by mdw on 2016/1/27.
 */
public class StringUtil {

    /**
     * 验证字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        if (str==null||str.length()==0){
            return  true;
        }

        return false;
    }
}
