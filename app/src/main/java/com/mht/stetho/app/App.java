package com.mht.stetho.app;


import android.app.Application;

import com.facebook.stetho.Stetho;
import com.mht.stetho.BuildConfig;

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        if (BuildConfig.DEBUG)
            initStetho();

//        Stetho.initializeWithDefaults(this);
    }

    private void initStetho() {
        Stetho.Initializer initializer = Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build();
        Stetho.initialize(initializer);
    }
}

/**
 * Notes:
 *
 * in AndroidManifest.xml
 *
 * Set <uses-permission android:name="android.permission.INTERNET" />
 *
 * and
 *
 * android:name=".app.App"
 *
 */
