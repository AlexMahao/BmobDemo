package com.example.mdw.bmobdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.BmobApplication;
import com.example.mdw.bmobdemo.R;
import com.example.mdw.bmobdemo.bean.User;
import com.example.mdw.bmobdemo.utils.AppManager;
import com.example.mdw.bmobdemo.utils.StringUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 手机
     */
    private EditText phoneEt;
    /**
     * 密码
     */
    private EditText passwordEt;

    private String password,phone;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneEt = ((EditText) findViewById(R.id.phone));

        passwordEt = ((EditText) findViewById(R.id.password));

        findViewById(R.id.btn_regist).setOnClickListener(this);

        findViewById(R.id.btn_forget_password).setOnClickListener(this);

        btn_login = ((Button) findViewById(R.id.btn_login));

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                phone = phoneEt.getText().toString().trim();
                password = passwordEt.getText().toString().trim();
                if(StringUtil.isBlank(phone)||StringUtil.isBlank(password)){
                    toast("用户名或密码为空");
                    return;
                }
                BmobUser.loginByAccount(this, phone, password, new LogInListener<User>() {

                    @Override
                    public void done(User user, BmobException e) {
                        // TODO Auto-generated method stub
                        if (user != null) {
                            toast("登录成功");
                            BmobApplication.user = user;
                            AppManager.getAppManager().finishAllActivity();

                            intent2Activity(SplashActivity.class);

                        }else{
                            toast("登录失败"+e.getMessage());
                        }
                    }
                });

                break;
            case R.id.btn_regist:
                intent2Activity(RegistActivity.class);
                break;

            case R.id.btn_forget_password:
                intent2Activity(ResetPasswordActivity.class);
                break;
        }
    }
}
