package com.kmgrv.networklibrary.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.SimpleImageLoader;
import com.android.volley.toolbox.Volley;
import com.kmgrv.networklib.controller.NetworkController;


public class AppController extends Application {

    private static AppController mInstance;


    public static final String TAG = AppController.class
            .getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        NetworkController.getInstance().setApplication(this);

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
