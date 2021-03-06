package com.miloway.milonote.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.miloway.milonote.R;

/**
 * Created by miloway on 2018/3/6.
 */

public class MiloToast {

    public static final int LENGTH_SHORT = 1000;

    public static final int LENGTH_LONG = 3000;

    public static final int SHOW = 1;

    public static final int CANCEL = 2;

    private static MiloToast toast;

    private int duration;

    private View view;

    private int gravity;
    /**
     * 窗口管理
     */
    private WindowManager mWM;

    private WindowManager.LayoutParams params;

    private final int OFFSET = 50;

    private MiloToast(Context context, String text, int duration, int gravity) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(R.layout.toast_layout, null);
        TextView tv = (TextView)view.findViewById(R.id.message);
        tv.setText(text);

        this.duration = duration;
        this.gravity = gravity;
        mWM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        mWM.getDefaultDisplay().getMetrics(dm);
        params = new WindowManager.LayoutParams();
        params.gravity = gravity;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.windowAnimations = R.style.AppTheme;
        params.y = (int) (dm.density * OFFSET);
    }



    public static MiloToast makeText(Context context, String text){
        return makeText(context, text, LENGTH_LONG);
    }

    public static MiloToast makeText(Context context, String text, int duration){
        return makeText(context, text, duration, Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
    }

    public static MiloToast makeText(Context context, String text, int duration, int gravity){
        if (context == null) {
            return null;
        }
        if (toast == null) {
            toast = new MiloToast(context, text, duration, gravity);
        }else {
            toast.cancel();
        }

        return toast;
    }



    public void show(){
        int delay = LENGTH_LONG;
        if (toast != null) {
            delay = toast.duration;
        }
        if (handler != null) {
            handler.removeMessages(CANCEL);
            handler.sendEmptyMessage(SHOW);
            handler.sendEmptyMessageDelayed(CANCEL, delay);
        }
    }

    private void cancel() {
        handler.removeMessages(CANCEL);
    }

    public void destroy() {
        if (handler != null) {
            handler.removeMessages(CANCEL);
            handler = null;
        }
        if (toast != null) {
            toast = null;
        }
    }

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW:
                    if (view.getParent() != null) {
                        mWM.removeView(view);
                    }
                    mWM.addView(view,params);
                    break;
                case CANCEL:{
                    if (view.getParent() != null) {
                        mWM.removeView(view);
                    }
                    break;
                }
            }
        }
    };

}
