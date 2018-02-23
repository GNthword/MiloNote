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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPreviewContent() {
        return previewContent;
    }

    public void setPreviewContent(String previewContent) {
        this.previewContent = previewContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}