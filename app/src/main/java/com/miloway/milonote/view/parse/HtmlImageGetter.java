package com.miloway.milonote.view.parse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.miloway.milonote.android.MiloApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

/**
 * Created by miloway on 2018/3/26.
 */

public class HtmlImageGetter implements Html.ImageGetter {

    private EditText view;
    private int height;
    private int width;
    private boolean isInit;
    public HtmlImageGetter(EditText view) {
        this.view = view;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener());
    }

    @Override
    public Drawable getDrawable(String source) {


        Context context = MiloApplication.getMiloApplication();

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = null;
        try {
            InputStream is =  new FileInputStream(source);
            bitmap = BitmapFactory.decodeStream(is,null,option);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (bitmap == null) {
            return null;
        }

        if (view != null) {
            int lineHeight = (int) (view.getPaint().getFontMetrics().bottom - view.getPaint().getFontMetrics().top);
            int maxShowWidth = width - lineHeight;
            if (bitmap.getWidth() > maxShowWidth) {
                int h = bitmap.getHeight() * maxShowWidth / bitmap.getWidth();
                bitmap = Bitmap.createScaledBitmap(bitmap, maxShowWidth, h, false);
            }


            int maxShowHeight = height - lineHeight * 4;
            if (bitmap.getHeight() > maxShowHeight) {
                int w = bitmap.getWidth() * maxShowHeight / bitmap.getHeight();
                bitmap = Bitmap.createScaledBitmap(bitmap, w, maxShowHeight, false);
            }
        }

        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());

        return drawable;
    }

    public void destroy() {
        view = null;
    }


    private class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            if (view == null) {
                return;
            }
            if (!isInit && view.getHeight() > 0) {
                width = view.getWidth();
                height = view.getHeight();
                isInit = true;
            }
        }
    }
}
