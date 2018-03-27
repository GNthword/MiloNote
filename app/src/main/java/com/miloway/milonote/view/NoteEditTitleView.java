package com.miloway.milonote.view;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miloway.milonote.R;
import com.miloway.milonote.listener.ChangeColorListener;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.util.DialogFactory;
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

    /**
     * 对话框
     */
    private Dialog dialog;

    private PopupWindow popupWindow;

    private String bgColor;

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

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
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
            showInsertDialog();
        }else if (id == R.id.iv_change_color) {
            showChangeColorDialog();
        }else if (id == R.id.tv_shot) {
            openCamera();
        }else if (id == R.id.tv_pick) {
            pickPicture();
        }

    }

    private void showInsertDialog() {
        dialog = DialogFactory.getInsertDialog(getContext());
        dialog.findViewById(R.id.tv_shot).setOnClickListener(this);
        dialog.findViewById(R.id.tv_pick).setOnClickListener(this);
        dialog.show();
    }

    private void openCamera() {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (listener != null) {
            listener.openCamera();
        }
    }

    private void pickPicture() {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (listener != null) {
            listener.pickPicture();
        }
    }


    private void showChangeColorDialog() {
        NoteEditTitleChangeColorView view = (NoteEditTitleChangeColorView) LayoutInflater.from(getContext()).inflate(R.layout.note_edit_title_view_change_color_layout, null);

        popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.MiloPopupWindow);
        //popupWindow.showAsDropDown(ivChangeColor);
        int x = ivChangeColor.getWidth();
        int y = ivChangeColor.getHeight() + 10;
        popupWindow.showAtLocation(ivChangeColor, Gravity.TOP,x,y);

        view.setBgColor(bgColor);
        view.setListener(new ChangeColorListener() {
            @Override
            public void changeColor(String bgColor) {
                if (listener != null) {
                    listener.changeColor(bgColor);
                }
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    public void setListener(NoteEditEventListener listener) {
        this.listener = listener;
    }

}
