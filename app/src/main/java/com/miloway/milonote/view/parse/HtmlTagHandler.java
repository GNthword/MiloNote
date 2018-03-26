package com.miloway.milonote.view.parse;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

/**
 * Created by miloway on 2018/3/26.
 */

public class HtmlTagHandler implements Html.TagHandler {
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

    }
}
