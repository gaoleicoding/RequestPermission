package com.gaolei.requestpermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * description: test
 * author: zlm
 * date: 2017/3/17 16:01
 */
public class SplashActivity extends BaseActivity {


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestPermission();

    }

    public void requestPermission() {
        requestPermission(this, new PermissionUtil.RequestPermissionCallBack() {

            @Override
            public void granted() {
//                Toast.makeText(SplashActivity.this, "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
                handler.sendEmptyMessageDelayed(0,3000);
            }

            @Override
            public void denied() {
//                Toast.makeText(SplashActivity.this, "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE});
    }

    public void onRestart() {
        super.onRestart();
        //跳转到设置界面后返回，重新检查权限
        requestPermission();
    }

}
