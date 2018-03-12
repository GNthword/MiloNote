package com.miloway.milonote.util;

import android.content.pm.ApplicationInfo;

import com.miloway.milonote.android.MiloApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by miloway on 2018/2/22.
 * 静态方法类
 */

public class MiloUtil {

    /*
     * in JDK8 use
     *      LongAdder for AtomicInteger
     *      Instant.now() for new Date()
     *      LocalDateTime fot Calendar
     *      DateTimeFormatter for SimpleDateFormat
     */

    public MiloUtil() {}


    public static boolean getApplicationDebugState(){
        if ( 0 != (MiloApplication.getMiloApplication().getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)) {
            return true;
        }

        return false;
    }

    public static String getCurrentFormatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date());
    }

    public static String getFormatDate(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date(time));
    }

    public static String getFormatDatePreview(long time) {
        if (time < 0) {
            return "";
        }
        SimpleDateFormat sdf;
        if (checkThisYear(time)) {
            sdf = new SimpleDateFormat("MM-dd",Locale.CHINA);
        }else {
            sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        }
        return sdf.format(new Date(time));
    }

    public static String getFormatDateFull(long time) {
        if (time < 0) {
            return "";
        }
        SimpleDateFormat sdf;
        if (checkThisYear(time)) {
            sdf = new SimpleDateFormat("MM-dd HH:mm:ss",Locale.CHINA);
        }else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        }
        return sdf.format(new Date(time));
    }

    /**
     * 校验年份 是否今年
     */
    private static boolean checkThisYear(long time){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        return currentYear == year;
    }
}
