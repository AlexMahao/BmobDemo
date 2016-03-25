package com.example.mdw.bmobdemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.BmobApplication;
import com.example.mdw.bmobdemo.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);



        mAvatar = ((ImageView) findViewById(R.id.more_avatar));

        if ( BmobApplication.user == null) {
            //未登录或注册
        } else {
            if (!TextUtils.isEmpty( BmobApplication.user.getUserIcon())) {
                ImageLoader.getInstance().displayImage( BmobApplication.user.getUserIcon(), mAvatar);
            }
        }

        mAvatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if ( BmobApplication.user == null) {
          intent2Activity(LoginActivity.class);
        }else{
            intent2Activity(ShowUserActivity.class);
        }
    }
}
