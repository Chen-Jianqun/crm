package com.itheima.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 */
public class DateUtils{

    //日期转字符
    public static String dateToString(Date source,String pattern) {
        String date=null;
        if(source!=null){
            DateFormat dateFormat=new SimpleDateFormat(pattern);
            date=dateFormat.format(source);
        }
        return date;
    }

    //字符转日期
    public static Date stringToDate(String source,String pattern)throws Exception{
        Date date=null;
        if(source!=null){
            DateFormat dateFormat=new SimpleDateFormat(pattern);
            date=dateFormat.parse(source);
        }
        return date;
    }
}
