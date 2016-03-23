package com.example.mdw.bmobdemo;

import android.app.Application;

import com.example.mdw.bmobdemo.bean.User;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created by mdw on 2016/1/27.
 */
public class BmobApplication extends Application {


    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();


        Bmob.initialize(this, "7df58d00c6689af835efc2dbdf376775");

        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this, "7df58d00c6689af835efc2dbdf376775");

    }
}
