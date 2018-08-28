package com.gaolei.runtimepermissionapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import static com.gaolei.runtimepermissionapplication.PermissionUtil.PERMISSION_CODE;

/**
 * description: 公共activity类
 * author: zlm
 * date: 2017/3/17 12:37
 */
public class BaseActivity extends AppCompatActivity {

    private PermissionUtil.RequestPermissionCallBack mRequestPermissionCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;

        switch (requestCode) {
            case PERMISSION_CODE: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            PermissionUtil.requestForeverDenyDialog(BaseActivity.this, permissions);

                        } else {
                            PermissionUtil.requestDenyDialog(BaseActivity.this, permissions);

                            //用户拒绝权限请求，但未选中“不再提示”选项
                        }
                        mRequestPermissionCallBack.denied();
                        hasAllGranted = false;
                        break;
                    }
                }
                if (hasAllGranted) {
                    mRequestPermissionCallBack.granted();
                }
            }
        }
    }

    /**
     * 发起权限请求
     *
     * @param context
     * @param permissions
     * @param callback
     */

    public void requestPermission(final Context context, final String[] permissions,
                                   PermissionUtil.RequestPermissionCallBack callback) {
        this.mRequestPermissionCallBack = callback;

        //如果所有权限都已授权，则直接返回授权成功,只要有一项未授权，则发起权限请求
        boolean isAllGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(((Activity) context), permissions, PERMISSION_CODE);
                isAllGranted = false;
                break;
            }
        }
        if (isAllGranted) {
            mRequestPermissionCallBack.granted();
        }
    }


}


