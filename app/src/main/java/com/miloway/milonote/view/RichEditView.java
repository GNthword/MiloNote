package com.miloway.milonote.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import com.miloway.milonote.view.input.MyTextWatch;
import com.miloway.milonote.view.parse.HtmlImageGetter;
import com.miloway.milonote.view.parse.HtmlTagHandler;
import com.miloway.milonote.view.tag.HTML_TAG;

import java.io.FileNotFoundException;

/**
 * Created by miloway on 2018/3/13.
 */

public class RichEditView extends EditText {
    private HtmlImageGetter imageGetter;
    private HtmlTagHandler tagHandler;

    public RichEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addTextChangedListener(new MyTextWatch());
        imageGetter = new HtmlImageGetter();
        tagHandler = new HtmlTagHandler();

    }

    public void insertPicture(Uri uri) {
        if (uri == null) {
            return;
        }
        Bitmap bitmap = null;
        try {

            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri),null,option);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        drawable.setBounds(0,0,200,200);
        ImageSpan span = new ImageSpan(drawable);

        SpannableStringBuilder builder = new SpannableStringBuilder("getResources");
        builder.setSpan(span,6,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //this.setText(builder);
        String str = super.getText().toString();

        String string = HTML_TAG.IMG_START + uri.getPath() + HTML_TAG.IMG_END;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.append(Html.fromHtml(string,Html.FROM_HTML_MODE_LEGACY,imageGetter,tagHandler));
        }else {
            builder.append(Html.fromHtml(string,imageGetter,tagHandler));
        }
        setText(builder);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    @Deprecated
    public Editable getText() {
        return super.getText();
    }

    /**
     * 获取内容
     */
    public String getContent() {
        return null;
        //return getText().toString();
    }

    /**
     * 获取内容预览
     */
    public String getPreviewContent() {
        return super.getText().toString();
    }

}
