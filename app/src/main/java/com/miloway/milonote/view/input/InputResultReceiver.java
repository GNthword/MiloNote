package com.miloway.milonote.view.input;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.miloway.milonote.listener.InputMethodListener;

/**
 * Created by miloway on 2018/4/12.
 */

public class InputResultReceiver extends ResultReceiver {

    private InputMethodListener listener;
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public InputResultReceiver(Handler handler, InputMethodListener listener) {
        super(handler);
        this.listener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (listener != null) {
            listener.notifyInputState(resultCode);
        }
    }
}
