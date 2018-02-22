package com.miloway.milonote.util;

import android.content.pm.ApplicationInfo;

import com.miloway.milonote.android.MiloApplication;

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
}
