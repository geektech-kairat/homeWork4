package com.example.lesson22;

import android.app.Application;

import com.example.lesson22.ui.sharePrefs.Share;

public class App extends Application {
    public static  App instance;
    public static  Share share;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        share = new Share(this);
    }
}
