package com.miloway.milonote.view.parse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.miloway.milonote.android.MiloApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

/**
 * Created by miloway on 2018/3/26.
 */

public class HtmlImageGetter implements Html.ImageGetter {
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

        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        drawable.setBounds(0,0,300,300);

        return drawable;
    }
}
