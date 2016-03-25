package com.xrz.bmob.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mdw.bmobdemo.R;

/**
 * Created by mdw on 2016/3/25.
 */
public class MoreActivity extends AppCompatActivity {

    private ImageView mUserImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);
        mUserImage = ((ImageView) findViewById(R.id.more_avatar));
    }
}
