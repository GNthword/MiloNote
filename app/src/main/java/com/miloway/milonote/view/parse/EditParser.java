package com.miloway.milonote.view.parse;

import android.os.Build;
import android.text.Editable;
import android.text.Html;

import com.miloway.milonote.util.LogTool;

/**
 * Created by miloway on 2018/4/13.
 */

public class EditParser {

    public static String getFormatContent(Editable text) {
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
    public static String getPreviewContent(Editable edit) {
        String text = EditParser.getFormatContent(edit);
        String text2 = edit.toString();
        int i = 0;
        int lines = 0;
        for (; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lines++;
            }
        }
        i = 0;
        lines = 0;

        for (; i < text2.length(); i++) {
            if (text2.charAt(i) == '\n') {
                lines++;
            }
        }
        return text;
    }
}
