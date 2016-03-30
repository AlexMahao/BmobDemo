package com.example.mdw.bmobdemo;

import android.app.Application;

import com.example.mdw.bmobdemo.bean.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;

/**
 * Created by mdw on 2016/1/27.
 */
public class BmobApplication extends Application {


    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "your Bmob key");

        BmobInstallation.getCurrentInstallation(this).save();

        user = BmobUser.getCurrentUser(this, User.class);

        initImageLoader();

    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();

        ImageLoader.getInstance().init(config);
    }
}
