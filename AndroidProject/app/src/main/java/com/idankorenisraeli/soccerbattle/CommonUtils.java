package com.idankorenisraeli.soccerbattle;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

// For making glide usage more convenient
public class CommonUtils {
    Context context;

    @SuppressLint("StaticFieldLeak")
    private static CommonUtils single_instance = null;
    // This WILL NOT cause a memory leak - *using application context only*

    private CommonUtils(Context context){
        this.context = context;
    }

    public static CommonUtils getInstance(){
        return single_instance;
    }

    public synchronized static CommonUtils
    initHelper(Context context){
        if(single_instance == null)
            single_instance = new CommonUtils(context.getApplicationContext());
        return single_instance;
    }


    public void setImageResource(@NonNull ImageView image, int resId){
        Glide.with(context)
                .load(resId)
                .into(image);
    }

    public void showToast(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }



}
