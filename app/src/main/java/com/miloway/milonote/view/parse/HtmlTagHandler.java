package com.miloway.milonote.view.parse;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;

import org.xml.sax.XMLReader;

import java.util.Stack;

/**
 * Created by miloway on 2018/3/26.
 */

public class HtmlTagHandler implements Html.TagHandler {

    private Stack<Integer> stack;
    private ImageClickListener listener;

    public HtmlTagHandler(ImageClickListener listener) {
        stack = new Stack<Integer>();
        this.listener = listener;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        if (tag.equals("imgTag")) {
            if (opening) {
                stack.push(output.length());
            }else {
                final int start = stack.pop();
                final int end = output.length();
                ClickableSpan span = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (listener != null) {
                            listener.imageClick(start, end);
                        }
                    }
                };
                ImageSpan[] spans = output.getSpans(start,end, ImageSpan.class);
                output.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }else if (tag.equals("aTag")) {
//            if (opening) {
//                stack.push(output.length());
//            }else {
//                int start = stack.pop();
//                int end = output.length();
//                ClickableSpan span = new ClickableSpan() {
//                    @Override
//                    public void onClick(View widget) {
//
//                    }
//                };
//                output.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
        }
    }

    public void destroy() {
        stack.clear();
        listener = null;
    }
}
