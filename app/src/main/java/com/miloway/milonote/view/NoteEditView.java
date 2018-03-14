package com.miloway.milonote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.miloway.milonote.R;

/**
 * Created by miloway on 2018/3/13.
 */

public class NoteEditView extends RelativeLayout {

    private NoteEditTitleView titleView;
    private NoteEditView editView;

    public NoteEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        titleView = findViewById(R.id.rl_title);
        editView = findViewById(R.id.rich_edit_view);
    }
}
