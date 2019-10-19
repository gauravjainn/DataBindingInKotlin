package com.example.gaurav.kotlin_navigationdrawer.settingStructure;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static final String TAG = "styleNSity";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Preferences.init(this);
//        MultiDex.install(this);

    }
}