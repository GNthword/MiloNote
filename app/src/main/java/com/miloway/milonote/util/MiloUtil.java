package com.miloway.milonote.util;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.miloway.milonote.android.MiloApplication;
import com.miloway.milonote.view.input.InputResultReceiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    /**
     * 获取cpu信息
     */
    public static String getCpuInfo() {
        String dir = "/proc/cpuinfo";
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(dir);
            bufferedReader = new BufferedReader(reader);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                //for name -> Hardware	: Qualcomm Technologies, Inc MSM8998
                //for cores -> CPU architecture: 8
                LogTool.d(temp);
            }
        } catch (IOException e) {
            LogTool.printStackTrace(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 获取cpu核心数量
     */
    public static void getCpuCores() {
        LogTool.d("cores", "" + Runtime.getRuntime().availableProcessors());
        File file = new File("/sys/devices/system/cpu/");
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name = pathname.getName();
                if (name.startsWith("cpu")) {
                    String[] splits = name.split("cpu");
                    return isInteger(splits[1]);
                }
                return false;
            }
        };
        File[] file1 = file.listFiles(filter);
        LogTool.d("cores2", "" + file1.length);
    }

    /**
     * 是否整数
     */
    public static boolean isInteger(String s) {
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        Pattern p = Pattern.compile("[-+]?[0-9]+");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static String getCameraPictureSavePath() {
        return MiloApplication.getMiloApplication().getExternalFilesDir(null) + "/img/";
    }
    /**
     * 相机生产的照片名称
     */
    public static String getCameraPictureName() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.CHINA);
        return "IMG_" + sdf.format(new Date()) + ".jpg";
    }


    public static String getPictureFullPath(Context context, Uri uri) {
        if (uri == null || context == null) {
            return null;
        }
        String path = null;
        String [] query = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri,query,null,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()){
                path = cursor.getString(0);
            }
            cursor.close();
        }
        return path;
    }


    public static void hideSoftKeyboard(Context context, View view, ResultReceiver rr) {
        if (context == null || view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (!imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS, rr)) {
                ((InputResultReceiver)rr).notify(0);
            }
        }
    }

    /**
     * 模拟后退
     */
    public static void goBack() {
        new Thread(){
            public void run() {
                try{
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                }
                catch (Exception e) {
                    LogTool.printStackTrace(e);
                }
            }
        }.start();
    }

}
