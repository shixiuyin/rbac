package com.hzit.bean;

public class User {


    private String userId;
    private String loginName;
    private String loginPwd;
    private String userName;
    private Integer status; // 0-可用 1-不可用
    private String email;
    private String address;
    private String remark;


    private String sessionId;//保存session的ID
    private String ip;
    private String loginTime;




    public User() {
    }

    public User(String userId, String loginName, String loginPwd, String userName, Integer status, String email, String address, String remark) {

        this.userId = userId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.userName = userName;
        this.status = status;
        this.email = email;
        this.address = address;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", ip='" + ip + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
