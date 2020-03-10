package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (TUserConfig)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TUserConfig implements Serializable {
    private static final long serialVersionUID = -82917268965221733L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 系统主题 dark暗色风格，light明亮风格
    */
    private String theme;
    /**
    * 系统布局 side侧边栏，head顶部栏
    */
    private String layout;
    /**
    * 页面风格 1多标签页 0单页
    */
    private String multiPage;
    /**
    * 页面滚动是否固定侧边栏 1固定 0不固定
    */
    private String fixSiderbar;
    /**
    * 页面滚动是否固定顶栏 1固定 0不固定
    */
    private String fixHeader;
    /**
    * 主题颜色 RGB值
    */
    private String color;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getMultiPage() {
        return multiPage;
    }

    public void setMultiPage(String multiPage) {
        this.multiPage = multiPage;
    }

    public String getFixSiderbar() {
        return fixSiderbar;
    }

    public void setFixSiderbar(String fixSiderbar) {
        this.fixSiderbar = fixSiderbar;
    }

    public String getFixHeader() {
        return fixHeader;
    }

    public void setFixHeader(String fixHeader) {
        this.fixHeader = fixHeader;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}