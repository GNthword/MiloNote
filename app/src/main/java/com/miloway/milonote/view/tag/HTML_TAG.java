package com.miloway.milonote.view.tag;

/**
 * Created by miloway on 2018/3/26.
 */

public class HTML_TAG {
    public static final String IMG_TAG = "imgTag";
    private static final String IMG_TAG_STRING = "<imgTag><img alt=\"img\" src=\"%s\"></img></imgTag>";
    //<a  style="color:FF0000" href="www.baidu.com">www.baidu.com</a>
    public static final String A_TAG = "aTag";
    private static final String A_TAG_STRING = "<aTag><a style=\"%s\" href=\"%s\" >%s</a></aTag>";
    public static final String BR_TAG = "<br>\n";
    public static final String IMG_TAG_START = "<img";
    private static final String A_TAG_DEFAULT_COLOR = "color:111111";


    public static String getImgTag(String src) {
        return getImgTag(src, true);
    }

    public static String getImgTag(String src, boolean newLine) {
        String temp =  IMG_TAG_STRING;
        if (newLine) {
            temp = "<br>" + temp;
        }
        return String.format(temp, src);
    }

    public static String getATag(String address){
        return getATag(address, address);
    }

    public static String getATag(String address, String name) {
        return getATag(address, name, A_TAG_DEFAULT_COLOR);
    }

    public static String getATag(String address, String name, String color) {
        return String.format(A_TAG_STRING, color, address, name);
    }
}
