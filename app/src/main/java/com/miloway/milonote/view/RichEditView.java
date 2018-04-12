package com.miloway.milonote.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
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
    private String content = "";

    private InputResultReceiver inputResultReceiver;
    private KeyListener keyListener;
    private MotionEvent firstEvent;
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
        imageGetter = new HtmlImageGetter(this);
        tagHandler = new HtmlTagHandler(this);
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

        content = formatContent(editable);

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

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取内容
     */
    public String getContent() {
        imageGetter.destroy();
        tagHandler.destroy();
        return null;
    }

    private String formatContent(Editable text) {
        String string = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            string = Html.toHtml(text, Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE);
        }else {
            string = Html.toHtml(text);
        }

        if (string.endsWith("\n")) {
            string = string.substring(0,string.length()-1);
        }

        string = string.replace("&lt;","<");
        string = string.replace("&gt;",">");

        return string;
    }

    /**
     * 获取内容预览
     */
    public String getPreviewContent() {
        String text = formatContent(super.getText());
        String text2 = super.getText().toString();
        int i = 0;
        int lines = 0;
        for (; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lines++;
            }
        }
        LogTool.d("1 " + text);
        LogTool.d("1 " + lines);
        i = 0;
        lines = 0;

        for (; i < text2.length(); i++) {
            if (text2.charAt(i) == '\n') {
                lines++;
            }
        }
        LogTool.d("2 " + text2);
        LogTool.d("2 " + lines);
        return text;
    }

    @Override
    public void imageClick(ImageSpan span) {
        if (firstEvent != null) {
            Rect rect = span.getDrawable().getBounds();
            if (rect.contains((int)firstEvent.getX(), (int)firstEvent.getY())) {
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
                firstEvent = event;
                break;
            case MotionEvent.ACTION_MOVE :
                break;
            case MotionEvent.ACTION_UP :
                if (firstEvent == null) {
                    break;
                }
                if (event.getX() != firstEvent.getX() ||
                        event.getY() != firstEvent.getY()) {
                    firstEvent = null;
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
