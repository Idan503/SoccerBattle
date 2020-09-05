package com.idankorenisraeli.soccerbattle.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.idankorenisraeli.soccerbattle.R;

import java.util.Objects;

// For making glide usage more convenient
public class CommonUtils {
    Context context;

    private static final int REQUEST_LOCATION = 1;

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

    // Need to send Activity in order to have the style
    public void showMaterialAlertDialog(MaterialDialogProperties properties){
        new MaterialAlertDialogBuilder(properties.getActivity())
                .setTitle(properties.getTitleLabel())
                .setMessage(properties.getMessage())
                .setNegativeButton(properties.getNoLabel(),properties.getOnNegative())
                .setPositiveButton(properties.getYesLabel(),properties.getOnPositive())
                .show();
    }

    //Getting last known location with GPS permission, we need activity here and not only context
    public LatLng getCurrentLocation(Activity activity){
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Objects.requireNonNull(activity),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        } else{
            Location currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(currentLocation!=null){
                return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            }else{
                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(currentLocation!=null)
                    return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            }
        }
        return null;
    }



}
