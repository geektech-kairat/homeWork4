package com.example.lesson22.ui;

import android.app.Application;

import com.example.lesson22.ui.sharePrefs.Share;

public class App extends Application {
    private static App instance;
    private static Share share;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        share = new Share(this);
    }
}
