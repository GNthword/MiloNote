package com.miloway.milonote.view.input;

import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.widget.EditText;

import com.miloway.milonote.util.LogTool;
import com.miloway.milonote.view.parse.EditParser;

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
        LogTool.d("line sel" + editText.getSelectionStart());
        LogTool.d("line " + getCursorLine());
        LogTool.d("line text " + getCursorLine());
    }

    private int getCursorLine(){
        int selection = editText.getSelectionStart();
        if (selection == -1) {
            return 0;
        }
        Layout layout = editText.getLayout();
        return layout.getLineForOffset(selection);
    }

    private String currentLine(){
        int selection = editText.getSelectionStart();
        if (editText.getText().length() < 1
                || selection < 0) {
            return "";
        }
        String string = EditParser.getFormatContent(editText.getText());

        String part1 = string.substring(0,selection);
        String part2 = string.substring(selection);
        int start , end;
        String [] part1s = part1.split("\n");
        String [] part2s = part2.split("\n");
        return "";
    }

    public void destroy() {
        editText = null;
    }
}
