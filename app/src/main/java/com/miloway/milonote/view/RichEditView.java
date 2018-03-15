package com.miloway.milonote.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by miloway on 2018/3/13.
 */

public class RichEditView extends EditText {
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
    }

    private class MyTextWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
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
        return getText().toString();
    }
}
