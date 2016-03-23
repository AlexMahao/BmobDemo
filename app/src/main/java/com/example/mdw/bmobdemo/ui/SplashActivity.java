package com.example.mdw.bmobdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.BmobApplication;
import com.example.mdw.bmobdemo.R;
import com.example.mdw.bmobdemo.bean.User;

import cn.bmob.v3.BmobUser;

public class SplashActivity extends BaseActivity {


    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text = ((TextView) findViewById(R.id.text));


        AlphaAnimation anim = new AlphaAnimation(0.0f,1f);
        anim.setDuration(2500);

        text.startAnimation(anim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user =  BmobUser.getCurrentUser(SplashActivity.this, User.class);
                if(user!=null){
                    BmobApplication.user = user;
                    intent2Activity(MainActivity.class);
                }else{
                    intent2Activity(LoginActivity.class);
                }
                finish();
            }
        },3000);

    }
}
