package com.miloway.milonote.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.miloway.milonote.view.input.MyTextWatch;
import com.miloway.milonote.view.parse.HtmlImageGetter;
import com.miloway.milonote.view.parse.HtmlTagHandler;
import com.miloway.milonote.view.tag.HTML_TAG;


/**
 * Created by miloway on 2018/3/13.
 */

public class RichEditView extends EditText {
    private HtmlImageGetter imageGetter;
    private HtmlTagHandler tagHandler;
    private String content = "";

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

    public void insertPicture(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

        String string = HTML_TAG.IMG_START + path + HTML_TAG.IMG_END;
        int selection = getSelectionStart();
        int selectionEnd = getSelectionEnd();

        Editable editable = super.getText();
        editable.insert(selection,string);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content = Html.toHtml(editable,Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE);
        }else {
            content = Html.toHtml(editable);
        }

        if (content.endsWith("\n")) {
            content = content.substring(0,content.length()-1);
        }

        content = content.replace("&lt;","<");
        content = content.replace("&gt;",">");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler));
        }else {
            setText(Html.fromHtml(content, imageGetter, tagHandler));
        }

        if (selection == selectionEnd) {
            selectionEnd++;
        }
        setSelection(selectionEnd);
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
