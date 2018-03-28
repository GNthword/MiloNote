package com.miloway.milonote.view.parse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
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
    public HtmlImageGetter(EditText view) {
        this.view = view;
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
            if (bitmap.getWidth() > view.getWidth()) {
                int height = bitmap.getHeight() * view.getWidth() / bitmap.getWidth();
                bitmap = Bitmap.createScaledBitmap(bitmap, view.getWidth(), height, false);
            }

            int lineHeight = (int) (view.getPaint().getFontMetrics().bottom - view.getPaint().getFontMetrics().top) * 3;
            int maxShowHeight = view.getHeight() - lineHeight;
            if (bitmap.getHeight() > maxShowHeight) {
                int width = bitmap.getWidth() * maxShowHeight / bitmap.getHeight();
                bitmap = Bitmap.createScaledBitmap(bitmap, width, maxShowHeight, false);
            }
        }

        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());

        return drawable;
    }

    public void destroy() {
        view = null;
    }
}
