package com.example.lesson22.ui.sharePrefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Share {

    public static final String APP_PREFERENCES = "my settings";
    public static final String BOOLEAN_FOR_SHOW_OPEN = "show";
    public static final String FOR_NAME = "name";
    private SharedPreferences sharedPreferences = null;


    public Share(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, context.MODE_PRIVATE);

    }

    public void saveBoardShown(Boolean isShown) {
        sharedPreferences.edit().putBoolean(BOOLEAN_FOR_SHOW_OPEN, isShown).apply();
    }

    public Boolean isBoardShown() {
        return sharedPreferences.getBoolean(BOOLEAN_FOR_SHOW_OPEN, false);
    }

    public void setForName(String name) {
        sharedPreferences.edit().putString(FOR_NAME, name).apply();
    }

    public String getForName() {
        return sharedPreferences.getString(FOR_NAME, "");
    }

}
