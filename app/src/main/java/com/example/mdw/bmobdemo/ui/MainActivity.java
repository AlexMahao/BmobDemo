package com.example.mdw.bmobdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.BmobApplication;
import com.example.mdw.bmobdemo.R;
import com.example.mdw.bmobdemo.bean.Person;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_exit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((TextView) findViewById(R.id.userId)).setText(BmobApplication.user.getObjectId());

        btn_exit_login = ((Button) findViewById(R.id.btn_exit_login));

        btn_exit_login.setOnClickListener(this);

        //addData();

        //queryData();

        //update();

        //deleteData();



    }

    /**
     * 删除
     */
    private void deleteData() {
        Person p2 = new Person();
        p2.setObjectId("d4f4070ba7");
        p2.delete(this, new DeleteListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this,
                        "创建数据失败：" + s,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 修改数据
     */
    private void update() {

        final Person p2 = new Person();
        p2.setAddress("北京朝阳");

        p2.update(this, "d4f4070ba7", new UpdateListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this,
                        "更新成功：" + p2.getUpdatedAt(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this,
                        "创建数据失败：" + s,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 查询数据
     */
    public void queryData(){

        BmobQuery<Person> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(this, "d4f4070ba7", new GetListener<Person>() {
            @Override
            public void onSuccess(Person person) {
                Toast.makeText(MainActivity.this,
                        person.getName(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this,
                        "创建数据失败：" + s,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 添加数据
     */
    private void addData() {
        final Person person = new Person();

        person.setName("Alex");
        person.setAddress("北京海淀");


        person.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,
                        "添加数据成功，返回objectId为：" + person.getObjectId(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this,
                        "创建数据失败：" + s,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit_login:
                BmobUser.logOut(this);   //清除缓存用户对象

                intent2Activity(LoginActivity.class);
               finish();

                break;
        }
    }
}
