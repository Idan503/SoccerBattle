package com.idankorenisraeli.soccerbattle;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate(){
    super.onCreate();

    GlideUtils.initHelper(this);

    }
}
