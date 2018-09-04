package com.gaolei.requestpermission.application;

import android.app.Application;
import android.content.Context;

import com.gaolei.requestpermission.crashhandler.CrashHandler;


public class CustomApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context=this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }


    }
