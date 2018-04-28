package com.miloway.milonote.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.miloway.milonote.listener.InputMethodListener;
import com.miloway.milonote.util.LogTool;
import com.miloway.milonote.util.MiloUtil;
import com.miloway.milonote.view.input.InputResultReceiver;
import com.miloway.milonote.view.input.MyTextWatch;
import com.miloway.milonote.view.parse.EditParser;
import com.miloway.milonote.view.parse.HtmlImageGetter;
import com.miloway.milonote.view.parse.HtmlTagHandler;
import com.miloway.milonote.view.parse.ImageClickListener;
import com.miloway.milonote.view.tag.HTML_TAG;


/**
 * Created by miloway on 2018/3/13.
 */

public class RichEditView extends EditText implements ImageClickListener, InputMethodListener {
    private HtmlImageGetter imageGetter;
    private HtmlTagHandler tagHandler;
    private MyTextWatch textWatch;
    private String content = "";

    private InputResultReceiver inputResultReceiver;
    private KeyListener keyListener;
    private MotionEvent clickEvent;
    public RichEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageGetter = new HtmlImageGetter(this);
        tagHandler = new HtmlTagHandler(this);
        textWatch = new MyTextWatch(this);
        addTextChangedListener(textWatch);
        setMovementMethod(LinkMovementMethod.getInstance());
        inputResultReceiver = new InputResultReceiver(null, this);
        keyListener = this.getKeyListener();

    }

    public void insertPicture(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

        String string = HTML_TAG.getImgTag(path);
        int selection = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selection == -1) {
            selection = 0;
            selectionEnd = 0;
        }

        Editable editable = super.getText();
        editable.insert(selection, string);

        content = EditParser.getFormatContent(editable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler));
        }else {
            setText(Html.fromHtml(content, imageGetter, tagHandler));
        }

        if (selection == selectionEnd) {
            selectionEnd+=3;
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

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取内容
     */
    public String getContent() {
        imageGetter.destroy();
        tagHandler.destroy();
        textWatch.destroy();
        return null;
    }

    /**
     * 获取内容预览
     */
    public String getPreviewContent() {
        return EditParser.getPreviewContent(super.getText());
    }

    @Override
    public void imageClick(ImageSpan span) {
        if (clickEvent != null) {
            Rect rect = span.getDrawable().getBounds();
            if (rect.contains((int) clickEvent.getX(), (int) clickEvent.getY())) {
                setKeyListener(null);
                MiloUtil.hideSoftKeyboard(getContext(), this, inputResultReceiver);
            }
        }
        //setKeyListener(null);
        //MiloUtil.hideSoftKeyboard(getContext(), this, inputResultReceiver);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                clickEvent = event;
                break;
            case MotionEvent.ACTION_MOVE :
                break;
            case MotionEvent.ACTION_UP :
                if (clickEvent == null) {
                    break;
                }
                if (event.getX() != clickEvent.getX() ||
                        event.getY() != clickEvent.getY()) {
                    clickEvent = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void notifyInputState(int resultCode) {
        setKeyListener(keyListener);
    }
}
