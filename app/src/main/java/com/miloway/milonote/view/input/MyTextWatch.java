package com.miloway.milonote.view.input;

import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.widget.EditText;

import com.miloway.milonote.util.LogTool;
import com.miloway.milonote.view.parse.EditParser;
import com.miloway.milonote.view.tag.HTML_TAG;

/**
 * Created by miloway on 2018/3/26.
 */

public class MyTextWatch implements TextWatcher {

    private EditText editText;
    public MyTextWatch(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        LogTool.d("line total" + EditParser.getFormatContent(editText.getText()));
        LogTool.d("line " + getCursorLine());
        LogTool.d("line text " + currentLineText());
    }

    private int getCursorLine(){
        int selection = editText.getSelectionStart();
        if (selection == -1) {
            return 0;
        }
        Layout layout = editText.getLayout();
        return layout.getLineForOffset(selection);
    }

    private String currentLineText(){
        int selection = editText.getSelectionStart();
        if (editText.getText().length() < 1
                || selection < 0) {
            return "";
        }
        String string = EditParser.getFormatContent(editText.getText());
        int curLine = getCursorLine();
        int start = -1, end = -1;
        for (int i = 0, line = 0; i <string.length(); i++) {
            if (start < 0 && curLine == line) {
                start = i;
            }
            if (string.charAt(i) == '\n') {
                if (curLine == line) {
                    end = i;
                    break;
                }
                line++;
            }else {
                String sub = string.substring(i);
                if (sub.startsWith(HTML_TAG.BR_TAG)){
                    if (curLine == line) {
                        end = i;
                        break;
                    }
                    line++;
                    i+=HTML_TAG.BR_TAG.length()-1;
                }
//                else if (sub.startsWith(HTML_TAG.IMG_TAG_START)) {
//                    if (curLine == line) {
//                        end = i;
//                        break;
//                    }
//                    line++;
//                    i+=HTML_TAG.IMG_TAG_START.length()-1;
//                }
            }
        }
        if (start < 0) {
            return "error";
        }
        if (end < 0) {
            end = string.length();
        }
        LogTool.d("line text ", string.length() + " " + start + " " + end);
        return string.substring(start, end);
    }

    public void destroy() {
        editText = null;
    }
}
