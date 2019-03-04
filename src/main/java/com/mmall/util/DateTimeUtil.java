package com.mmall.util;/*
@author 黄大宁Rhinos
@date 2019/3/4 - 14:53
**/

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.sound.midi.Soundbank;
import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String dateTimeStr,String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String date2Str(Date date,String formatStr){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }
    public static Date str2Date(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String date2Str(Date date){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
    public static void main(String[] args) {
        System.out.println(DateTimeUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtil.str2Date("2010-01-01 11:11:12","yyyy-MM-dd HH:mm:ss"));
        //date2string
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_FORMAT);
        System.out.println(sdf.format(date));
        //string2date
        String str = "2010-01-01 11:11:12";
        try {
            System.out.println(sdf.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        date2str
        System.out.println(new DateTime(date).toString(STANDARD_FORMAT));
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime("2010-01-01 11:11:12");
        System.out.println(dateTime.toString(STANDARD_FORMAT));
    }
}
