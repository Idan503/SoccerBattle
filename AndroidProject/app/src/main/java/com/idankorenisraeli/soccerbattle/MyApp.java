package com.idankorenisraeli.soccerbattle;

import android.app.Application;
import android.view.View;

public class MyApp extends Application {
    @Override
    public void onCreate(){
    super.onCreate();


    GlideUtils.initHelper(this);

    }
}
