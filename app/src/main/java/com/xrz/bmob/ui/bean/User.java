package com.xrz.bmob.ui.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by mdw on 2016/3/25.
 */
public class User extends BmobObject {

    private String userId;

    private String phone;

    private String password;

    private String imgUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
