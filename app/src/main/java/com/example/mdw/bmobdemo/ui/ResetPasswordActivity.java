package com.example.mdw.bmobdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.R;
import com.example.mdw.bmobdemo.utils.AppManager;
import com.example.mdw.bmobdemo.utils.StringUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;

/**
 * 重置密码
 */
public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText phoneEt, passwordEt,authCodeEt;

    private Button registBtn,getAuthCodeBtn;


    private String phone ,authCode,password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        phoneEt = (EditText) findViewById(R.id.phone);

        authCodeEt = (EditText) findViewById(R.id.authCode);

        passwordEt = (EditText) findViewById(R.id.password);

        registBtn = (Button) findViewById(R.id.regist);

        getAuthCodeBtn = (Button) findViewById(R.id.send_authCode);

        getAuthCodeBtn.setOnClickListener(this);

        registBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist:
                authCode = authCodeEt.getText().toString().trim();
                password = passwordEt.getText().toString().trim();
                if(StringUtil.isBlank(authCode)){
                    authCodeEt.setError("验证码为空");
                    return;
                }
                if(StringUtil.isBlank(password)){
                    passwordEt.setError("密码为空");
                    return;
                }


                BmobUser.resetPasswordBySMSCode(this, authCode, password, new ResetPasswordByCodeListener() {

                    @Override
                    public void done(BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {
                            AppManager.getAppManager().finishActivity(LoginActivity.class);
                            toast("重置密码成功");
                            intent2Activity(LoginActivity.class);
                            AppManager.getAppManager().finishActivity(ResetPasswordActivity.class);
                        } else {
                            Log.i("smile", "重置失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                        }
                    }
                });

                break;
            case R.id.send_authCode:
                phone = phoneEt.getText().toString().trim();

                if(phone.length()==0){
                    phoneEt.setError("手机号为空");
                    return;
                }
                //获取短信验证码
                BmobSMS.requestSMSCode(this, phone, "regist", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null) {
                            toast("发送短信成功");
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
                break;
        }
    }
}
