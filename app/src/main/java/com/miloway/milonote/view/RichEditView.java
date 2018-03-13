package com.miloway.milonote.view;

import android.content.Context;
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
    }
}
