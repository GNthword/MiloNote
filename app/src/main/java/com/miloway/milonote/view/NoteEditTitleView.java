package com.miloway.milonote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miloway.milonote.R;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.util.MiloUtil;

/**
 * Created by miloway on 2018/3/13.
 */

public class NoteEditTitleView extends RelativeLayout implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTime;
    private TextView tvAlarmClock;
    private ImageView ivInsert;
    private ImageView ivChangeColor;

    private NoteEditEventListener listener;

    public NoteEditTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteEditTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initEvent();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvAlarmClock = (TextView) findViewById(R.id.tv_alarm_clock);
        ivInsert = (ImageView) findViewById(R.id.iv_insert);
        ivChangeColor = (ImageView) findViewById(R.id.iv_change_color);
    }

    private void initEvent() {
        ivBack.setOnClickListener(this);
        tvAlarmClock.setOnClickListener(this);
        ivInsert.setOnClickListener(this);
        ivChangeColor.setOnClickListener(this);
    }

    public void setModifyTime(long time) {
        tvTime.setText(MiloUtil.getFormatDatePreview(time));
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            if (listener != null) {
                listener.goBack();
            }
        }else if (id == R.id.tv_alarm_clock) {

        }else if (id == R.id.iv_insert) {

        }else if (id == R.id.iv_change_color) {

        }

    }

    public void setListener(NoteEditEventListener listener) {
        this.listener = listener;
    }

}
