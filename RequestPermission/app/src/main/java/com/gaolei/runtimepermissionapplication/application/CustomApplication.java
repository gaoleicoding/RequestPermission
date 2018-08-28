package com.gaolei.runtimepermissionapplication.application;

import android.app.Application;
import android.content.Context;

import com.gaolei.runtimepermissionapplication.crashhandler.CrashHandler;
import com.gaolei.runtimepermissionapplication.crashhandler.CrashHandler2;


public class CustomApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context=this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        CrashHandler2 crashHandler2 = CrashHandler2.getInstance();
        crashHandler2.init(getApplicationContext());
    }


    }
