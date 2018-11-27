package com.hzit.bean;

import java.util.List;

/**
 * 资源 权限
 */
public class Menu {


    private String menuId;
    private String menuName;
    private String parentId;
    private String linkUrl;
    private String status;
    private String icon;
    private String remark;
    private String checked;

    private List<Menu> subMenu; //存放子菜单的内容

    @Override
    public String toString() {
        return "Menu{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", status='" + status + '\'' +
                ", icon='" + icon + '\'' +
                ", remark='" + remark + '\'' +
                ", checked='" + checked + '\'' +
                ", subMenu=" + subMenu +
                '}';
    }

    public Menu() {

    }

    public Menu(String menuId, String menuName, String parentId, String linkUrl, String status, String icon, String remark, String checked, List<Menu> subMenu) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentId = parentId;
        this.linkUrl = linkUrl;
        this.status = status;
        this.icon = icon;
        this.remark = remark;
        this.checked = checked;
        this.subMenu = subMenu;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }
}
