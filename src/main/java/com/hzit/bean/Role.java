package com.hzit.bean;

public class Role {

    private String roleId;
    private String roleName;
    private String roleDesc;
    private String roleActive;
    private String checked;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleActive() {
        return roleActive;
    }

    public void setRoleActive(String roleActive) {
        this.roleActive = roleActive;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Role() {
    }

    public Role(String roleId, String roleName, String roleDesc, String roleActive, String checked) {

        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleActive = roleActive;
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", roleActive='" + roleActive + '\'' +
                ", checked='" + checked + '\'' +
                '}';
    }
}
