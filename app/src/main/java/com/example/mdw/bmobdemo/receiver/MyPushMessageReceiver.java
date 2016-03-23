package com.example.mdw.bmobdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by mdw on 2016/1/28.
 */
public class MyPushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("msg")){
            Log.e("MyPushMessageReceiver","客户端收到的推送内容"+intent.getStringExtra("msg"));
        }
    }
}
