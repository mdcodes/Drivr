package com.mdrahorat4563.drivr.Models;

/**
 * Created by Michal Drahorat on 10/18/2017.
 */

public class LoginModel {
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginModel(){}
    public LoginModel(int loginId, String userName, String password) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
    }

    private int loginId;
    private String userName;
    private  String password;
}
