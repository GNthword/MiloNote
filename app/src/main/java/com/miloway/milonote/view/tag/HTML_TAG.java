package com.miloway.milonote.view.tag;

/**
 * Created by miloway on 2018/3/26.
 */

public class HTML_TAG {
    public static final String IMG_TAG = "imgTag";
    private static final String IMG_TAG_STRING = "<br><imgTag><img alt=\"img\" src=\"%s\"></img></imgTag><br>";
    //<a  style="color:FF0000" href="www.baidu.com">www.baidu.com</a>
    public static final String A_TAG = "aTag";
    private static final String A_TAG_STRING = "<aTag><a style=\"%s\" href=\"%s\" >%s</a></aTag>";


    public static String getImgTag(String src) {
        return String.format(IMG_TAG_STRING, src);
    }

    public static String getATag(String address){
        return getATag(address, address);
    }

    public static String getATag(String address, String name) {
        return getATag(address, name, null);
    }

    public static String getATag(String address, String name, String color) {
        return String.format(A_TAG_STRING, color, address, name);
    }
}
