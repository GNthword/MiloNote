package com.miloway.milonote.obj;

/**
 * Created by miloway on 2018/2/22.
 * 实体类
 */

public class MiloNote {

    private long id;
    /**
     * 父文件夹位置
     */
    private long parentId;
    /**
     * 当前位置
     */
    private long position;
    /**
     * 文件夹还是内容
     */
    private String type;
    /**
     * 日期
     */
    private String createdDate;
    private String modifiedDate;
    /**
     * 预览
     */
    private String previewContent;
    private String content;
    /**
     * 提醒日期
     */
    private String alertDate;
    /**
     * 背景色
     */
    private String bgColor;


}
