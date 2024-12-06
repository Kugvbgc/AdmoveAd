package com.khair.admovead.monetization;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class Sdk_Initialize extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {});
    }
}
