package com.andforce.utils;

import java.util.Calendar;

/**
 * Created by andforce on 2018/1/23.
 */
public class TimeUtils {

    public static String getUTCTimeStr(String format) {
        StringBuilder UTCTimeBuffer = new StringBuilder();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        return  String.format(format, year, month, day, hour, minute, second);
    }

    private static final String sFormat = "%04d-%02d-%02dT%02d:%02d:%02d";

    public static String getTimeStr() {
        StringBuilder UTCTimeBuffer = new StringBuilder();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        return  String.format(sFormat, year, month, day, hour, minute, second);
    }

}
