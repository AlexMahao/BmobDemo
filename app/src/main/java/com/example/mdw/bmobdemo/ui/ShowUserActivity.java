package com.example.mdw.bmobdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mdw.bmobdemo.BaseActivity;
import com.example.mdw.bmobdemo.BmobApplication;
import com.example.mdw.bmobdemo.R;
import com.example.mdw.bmobdemo.utils.AppManager;

import java.io.File;
import java.io.FileOutputStream;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 展示用户信息
 * Created by mdw on 2016/3/25.
 */
public class ShowUserActivity extends BaseActivity {

    private TextView mIdTextView;
    private TextView mPhone;
    private TextView mPassword;
    private TextView mIcon;


    private ProgressDialog dialog;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        dialog = new ProgressDialog(this);

        mIdTextView = ((TextView) findViewById(R.id.tv_id));

        mPhone = ((TextView) findViewById(R.id.tv_phone));

        mPassword = ((TextView) findViewById(R.id.tv_password));

        mIcon = ((TextView) findViewById(R.id.tv_icon));


        mIdTextView.setText(BmobApplication.user.getObjectId());

        mPhone.setText(BmobApplication.user.getMobilePhoneNumber());

        //mPassword.setText(user.get);

        mIcon.setText(BmobApplication.user.getUserIcon() + "");
    }

    public void exit(View view) {
        BmobUser.logOut(this);

        BmobApplication.user = null;
        AppManager.getAppManager().finishAllActivity();

        intent2Activity(SplashActivity.class);
    }


    public void upload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("info",""+requestCode+"``"+resultCode);
        if (resultCode == RESULT_OK && requestCode == 100) {

            /**
             * 获取图片地址
             */
            Uri uri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cusor = getContentResolver().query(uri, filePathColumn, null, null, null);
            cusor.moveToFirst();

            int columnIndex = cusor.getColumnIndex(filePathColumn[0]);
            final String picturePath = cusor.getString(columnIndex);
           // cusor.close();

            dialog.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        String path = compressBitmap(picturePath);

                        final BmobFile bmobFile = new BmobFile(new File(path));
                        bmobFile.uploadblock(getApplicationContext(), new UploadFileListener() {

                            @Override
                            public void onSuccess() {

                                String url = bmobFile.getFileUrl(getApplicationContext());
                                BmobApplication.user.setUserIcon(url);
                                BmobApplication.user.update(getApplicationContext());
                                dialog.dismiss();
                            }

                            @Override
                            public void onProgress(Integer value) {
                                // TODO Auto-generated method stub
                                // 返回的上传进度（百分比）
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                // TODO Auto-generated method stub
                                toast("上传文件失败：" + msg);
                                dialog.dismiss();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }

    }




    /**
     * 压缩图片并回传图片地址
     * @param url
     * @return
     * @throws Exception
     */
    public String compressBitmap(String url) throws Exception {

        Log.i("info",url);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap sourceBitmap = BitmapFactory.decodeFile(url, options);
        int width = options.outWidth;
        int hegith = options.outHeight;

        int sampleSize = width / 120 > hegith / 120 ? hegith / 120 : width / 120;

        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(url, options);

        String path = getExternalCacheDir() + "/" + System.currentTimeMillis() + ".png";

        FileOutputStream f = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, f);
        f.close();

        return  path;

    }
}
