package com.miloway.milonote.obj;

import java.io.Serializable;

/**
 * Created by miloway on 2018/2/22.
 * 实体类
 */

public class MiloNote implements Serializable{


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
     * 创建日期<br/>
     * 新建便签的时间，不管便签是否最终被保存
     * @see System#currentTimeMillis()
     */
    private long createdDate;
    /**
     * 修改日期<br/>
     * 保存便签的时间，通常在一开始就和创建日期不同
     * @see System#currentTimeMillis()
     */
    private long modifiedDate;
    /**
     * 标题
     */
    private String title;
    /**
     * 预览
     */
    private String previewContent;
    /**
     * 提醒日期
     */
    private long alertDate;
    /**
     * 背景色
     */
    private String bgColor;
    /**
     * 密码类型
     */
    private String passwordType;
    /**
     * 密码
     */
    private String password;


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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPreviewContent() {
        return previewContent;
    }

    public void setPreviewContent(String previewContent) {
        this.previewContent = previewContent;
    }

    public long getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(long alertDate) {
        this.alertDate = alertDate;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
