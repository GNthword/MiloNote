package com.miloway.milonote.util;

/**
 * Created by miloway on 2018/2/22.
 * 常量类
 */

public class MiloConstants {

    /*
     * 便签内容类型
     */
    public static final String NOTE_CONTENT_TYPE_CONTENT = "content";
    public static final String NOTE_CONTENT_TYPE_FOLDER = "folder";
    /*
     * 最外层parent_id
     */
    public static final int NOTE_FOLDER_PARENT_ID_TOP_LEVEL = 0;
    public static final long NOTE_ALERT_TIME_DEFAULT_VALUE = -1;
    public static final long NOTE_ID_DEFAULT_VALUE = -1;
    /*
     * columnIndex
     */
    public static final int NOTE_COLUMN_INDEX_ID = 0;
    public static final int NOTE_COLUMN_INDEX_PARENT_ID = 1;
    public static final int NOTE_COLUMN_INDEX_POSITION = 2;
    public static final int NOTE_COLUMN_INDEX_TYPE = 3;
    public static final int NOTE_COLUMN_INDEX_CREATE_DATE = 4;
    public static final int NOTE_COLUMN_INDEX_MODIFIED_DATE = 5;
    public static final int NOTE_COLUMN_INDEX_TITLE = 6;
    public static final int NOTE_COLUMN_INDEX_PREVIEW_CONTENT = 7;
    public static final int NOTE_COLUMN_INDEX_ALERT_DATE = 8;
    public static final int NOTE_COLUMN_INDEX_BG_COLOR = 9;
    public static final int NOTE_COLUMN_INDEX_PASSWORD_TYPE = 10;
    public static final int NOTE_COLUMN_INDEX_PASSWORD = 11;
    public static final int NOTE_COLUMN_INDEX_CONTENT = 1;

    /*
     * 便签对象传递name
     */
    public static final String NOTE_OBJECT_SERIALIZE_KEY = "milo_note";

    /**
     * startActivityForResult
     */
    public static final int RESULT_TYPE_PICK_PICTURE = 1;
    public static final int RESULT_TYPE_OPEN_CAMERA = 2;
}
