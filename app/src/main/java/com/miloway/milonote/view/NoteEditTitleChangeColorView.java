package com.miloway.milonote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.miloway.milonote.R;
import com.miloway.milonote.listener.ChangeColorListener;
import com.miloway.milonote.util.BackgroundTool;

/**
 * Created by miloway on 2018/3/19.
 */

public class NoteEditTitleChangeColorView extends RelativeLayout implements View.OnClickListener {

    private ImageView ivTriangle;
    private LinearLayout llYellow;
    private LinearLayout llBlue;
    private LinearLayout llWhite;
    private LinearLayout llGreen;
    private LinearLayout llRed;
    /**
     * 背景色
     */
    private String bgColor;
    private ImageView ivSelected;

    private ChangeColorListener listener;

    public NoteEditTitleChangeColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initEvent();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void initView() {
        ivTriangle = (ImageView) findViewById(R.id.iv_triangle);
        llYellow = (LinearLayout) findViewById(R.id.ll_yellow);
        llBlue = (LinearLayout) findViewById(R.id.ll_blue);
        llWhite = (LinearLayout) findViewById(R.id.ll_white);
        llGreen = (LinearLayout) findViewById(R.id.ll_green);
        llRed = (LinearLayout) findViewById(R.id.ll_red);

        ivSelected = new ImageView(getContext());
        ivSelected.setImageResource(R.drawable.edit_view_change_color_picker_selected);
    }

    private void initEvent() {
        llYellow.setOnClickListener(this);
        llBlue.setOnClickListener(this);
        llWhite.setOnClickListener(this);
        llGreen.setOnClickListener(this);
        llRed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String bgColor = null;
        if (id == R.id.ll_yellow) {
            bgColor = BackgroundTool.NOTE_BACKGROUND_COLOR_YELLOW;
        }else if (id == R.id.ll_blue) {
            bgColor = BackgroundTool.NOTE_BACKGROUND_COLOR_BLUE;
        }else if (id == R.id.ll_white) {
            bgColor = BackgroundTool.NOTE_BACKGROUND_COLOR_WHITE;
        }else if (id == R.id.ll_green) {
            bgColor = BackgroundTool.NOTE_BACKGROUND_COLOR_GREEN;
        }else if (id == R.id.ll_red) {
            bgColor = BackgroundTool.NOTE_BACKGROUND_COLOR_RED;
        }
        setBgColor(bgColor);
    }

    public void setBgColor(String bgColor) {
        if (bgColor == null || bgColor.equals(this.bgColor)) {
            return;
        }
        this.bgColor = bgColor;
        if (llYellow.getChildCount() > 0) {
            llYellow.removeAllViews();
        }else if (llBlue.getChildCount() > 0) {
            llBlue.removeAllViews();
        }else if (llWhite.getChildCount() > 0) {
            llWhite.removeAllViews();
        }else if (llGreen.getChildCount() > 0) {
            llGreen.removeAllViews();
        }else if (llRed.getChildCount() > 0) {
            llRed.removeAllViews();
        }
        if (BackgroundTool.NOTE_BACKGROUND_COLOR_YELLOW.equals(bgColor)) {
            llYellow.addView(ivSelected);
        }else if (BackgroundTool.NOTE_BACKGROUND_COLOR_BLUE.equals(bgColor)) {
            llBlue.addView(ivSelected);
        }else if (BackgroundTool.NOTE_BACKGROUND_COLOR_WHITE.equals(bgColor)) {
            llWhite.addView(ivSelected);
        }else if (BackgroundTool.NOTE_BACKGROUND_COLOR_GREEN.equals(bgColor)) {
            llGreen.addView(ivSelected);
        }else if (BackgroundTool.NOTE_BACKGROUND_COLOR_RED.equals(bgColor)) {
            llRed.addView(ivSelected);
        }
        if (listener != null) {
            listener.changeColor(bgColor);
        }
    }

    public void setListener(ChangeColorListener listener) {
        this.listener = listener;
    }

}
