package com.itheima.util;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source){
        if(source==null){
            throw new RuntimeException("请输入正确数据");
        }
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return  dateFormat.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("传入正确格式");
        }
    }
}
