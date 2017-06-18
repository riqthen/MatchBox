package com.example.administrator.liveplayer;

import android.app.Application;

import io.vov.vitamio.Vitamio;

/**
 * Created by Administrator on 2016/12/19.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Vitamio.initialize(this);
    }
}
