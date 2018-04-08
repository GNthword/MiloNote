package com.miloway.milonote.util;

/**
 * Created by miloway on 2018/2/22.
 * 日志类
 */

public class LogTool {

    private static String TAG = "log_tool";
    private static boolean IS_OPEN = false;

    public static void setLogState(boolean open) {
        IS_OPEN = open;
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (IS_OPEN) {
            android.util.Log.d(tag, message);
        }
    }

    public static void e(String message){
        e(TAG, message);
    }

    public static void e(String tag, String message){
        if (IS_OPEN) {
            android.util.Log.e(tag, message);
        }
    }

    public static void w(String message){
        w(TAG, message);
    }

    public static void w(String tag, String message){
        if (IS_OPEN) {
            android.util.Log.d(tag, message);
        }
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (IS_OPEN) {
            android.util.Log.i(tag, message);
        }
    }

    public static void printStackTrace(Exception e) {
        if (IS_OPEN) {
            e.printStackTrace();
        }
    }
}
