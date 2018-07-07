package com.example.digitalgrouptest;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

/**
 * Created by Andrei on 07.07.2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this, "ca-app-pub-7332901236003749~4531662218");
    }
}
