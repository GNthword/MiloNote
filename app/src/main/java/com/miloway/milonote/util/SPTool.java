package com.miloway.milonote.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by miloway on 2018/2/23.
 * SharedPreferences Tool
 */

public class SPTool {

    /**
     * 更好的方式是吧常量定义分开，这里数据较少就合一起
     */
    public static String SP_FILE_LAUNCH_CONFIG = "sp_file_launch_config";
    public static String SP_FILE_USER_CONFIG = "sp_file_user_config";
    public static String SP_KEY_LAUNCH_INIT = "sp_key_launch_init";

    public static void put(Context context, String fileName, String key, String value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void put(Context context, String fileName, String key, long value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void put(Context context, String fileName, String key, int value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void put(Context context, String fileName, String key, boolean value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void put(Context context, String fileName, String key, float value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void put(Context context, String fileName, String key, Set<String> value){
        if (context == null){
            return;
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static boolean get(Context context, String fileName, String key, boolean defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getBoolean(key,defValue);
        }
        return defValue;
    }

    public static float get(Context context, String fileName, String key, float defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getFloat(key,defValue);
        }
        return defValue;
    }

    public static int get(Context context, String fileName, String key, int defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getInt(key,defValue);
        }
        return defValue;
    }

    public static long get(Context context, String fileName, String key, long defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getLong(key,defValue);
        }
        return defValue;
    }

    public static String get(Context context, String fileName, String key, String defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getString(key,defValue);
        }
        return defValue;
    }

    public static Set<String> get(Context context, String fileName, String key, Set<String> defValue){
        if (context == null){
            return defValue;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp != null){
            return sp.getStringSet(key,defValue);
        }
        return defValue;
    }



}
