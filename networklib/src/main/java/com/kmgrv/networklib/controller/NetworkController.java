package com.kmgrv.networklib.controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.SimpleImageLoader;
import com.android.volley.toolbox.Volley;

public class NetworkController {

    private static NetworkController mInstance;

    private Application application;

    private RequestQueue mRequestQueue;
    private static SimpleImageLoader mImageLoader;

    public static final String TAG = NetworkController.class
            .getSimpleName();

    public static synchronized NetworkController getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkController();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplication());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public SimpleImageLoader getImageLoader() {
        DiskLruBasedCache.ImageCacheParams cacheParams = new DiskLruBasedCache.ImageCacheParams(getApplication(), "images");
        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            mImageLoader = new SimpleImageLoader(getApplication(), cacheParams);
//            throw new IllegalStateException("ImageLoader not initialized");
            return mImageLoader;
        }
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
