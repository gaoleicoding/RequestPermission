package com.gaolei.runtimepermissionapplication;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/** 
 * description: test
 * author: zlm
 * date: 2017/3/17 16:01
*/
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.request).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    public void requestPermission(){
        requestPermission(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},new PermissionUtil.RequestPermissionCallBack() {
            @Override
            public void granted() {
//                Toast.makeText(MainActivity.this, "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
            }

            @Override
            public void denied() {
//                Toast.makeText(MainActivity.this, "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onRestart(){
        super.onRestart();
        //跳转到设置界面后，重现检查权限
        requestPermission();
    }

}
