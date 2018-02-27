package com.miloway.milonote.util;

import android.content.pm.ApplicationInfo;

import com.miloway.milonote.android.MiloApplication;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by miloway on 2018/2/22.
 * 静态方法类
 */

public class MiloUtil {

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
}
