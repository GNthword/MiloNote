package com.miloway.milonote.view.input;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by miloway on 2018/3/26.
 */

public class MyTouchListener implements View.OnTouchListener {
    private int inputMode = InputMode.KEY_BOARD;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public void setInputMode(int inputMode) {
        this.inputMode = inputMode;
    }
}
