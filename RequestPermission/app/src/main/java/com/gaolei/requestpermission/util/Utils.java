package com.gaolei.requestpermission.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

public class Utils {

    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo taskInfo = manager.getRunningTasks(1).get(0);
        ComponentName componentInfo = taskInfo.topActivity;
        String shortClassName = componentInfo.getShortClassName();    //类名
//        String className = info.topActivity.getClassName();              //完整类名
//        String packageName = info.topActivity.getPackageName();
        int index=shortClassName.lastIndexOf(".");
        shortClassName=shortClassName.substring(index+1);
        return shortClassName;
    }
}
