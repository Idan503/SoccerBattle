package com.idankorenisraeli.soccerbattle;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

// For making glide usage more convenient
public class GlideUtils {
    private Glide glide;
    private static GlideUtils single_instance = null;

    private GlideUtils(Context context){
        glide = Glide.get(context);
    }

    public void setImageResource(@NonNull ImageView image, int resId){
        Glide.with(glide.getContext())
                .load(resId)
                .into(image);
    }

    public static GlideUtils getInstance(){
        return single_instance;
    }

    public static GlideUtils initHelper(Context context){
        if(single_instance == null)
            single_instance = new GlideUtils(context);
        return single_instance;
    }

}
