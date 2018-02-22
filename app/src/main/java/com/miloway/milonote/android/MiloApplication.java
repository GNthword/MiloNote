package com.miloway.milonote.android;

import android.app.Application;

/**
 * Created by miloway on 2018/2/22.
 */

public class MiloApplication extends Application {

    private static MiloApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getMiloApplication(){
        return application;
    }
}
