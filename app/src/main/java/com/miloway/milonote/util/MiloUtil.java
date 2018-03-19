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
            if (checkToday(time)) {
                sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
            }else {
                sdf = new SimpleDateFormat("MM-dd", Locale.CHINA);
            }
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
    private static boolean checkThisYear(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        return currentYear == year;
    }

    /**
     * 校验日期 是否今天
     */
    private static boolean checkToday(long time) {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(new Date(time));
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        return today == day;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int resId = MiloApplication.getMiloApplication().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return MiloApplication.getMiloApplication().getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    /**
     * 获取导航栏高度（虚拟按键）
     */
    public static int getNavigationBarHeight() {
        int resId = MiloApplication.getMiloApplication().getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        boolean show = MiloApplication.getMiloApplication().getResources().getBoolean(resId);
        if (show) {
            resId = MiloApplication.getMiloApplication().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return MiloApplication.getMiloApplication().getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }





}
