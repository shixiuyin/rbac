package com.hzit.bean;

import java.util.List;

/**
 * 加载左边导航条
 */
public class Nav {

    private String text;
    private String icon;
    private String href;

    private List<Nav> subset; //存放子菜单的内容

    @Override
    public String toString() {
        return "Nav{" +
                "text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", subset=" + subset +
                '}';
    }

    public Nav() {
    }

    public Nav(String text, String icon, String href, List<Nav> subset) {

        this.text = text;
        this.icon = icon;
        this.href = href;
        this.subset = subset;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Nav> getSubset() {
        return subset;
    }

    public void setSubset(List<Nav> subset) {
        this.subset = subset;
    }
}
