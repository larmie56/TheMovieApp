package com.sholasstore.themovieapp;

import android.app.Application;

import com.sholasstore.themovieapp.di.AppComponent;
import com.sholasstore.themovieapp.di.AppModule;
import com.sholasstore.themovieapp.di.DaggerAppComponent;
import com.sholasstore.themovieapp.di.NetworkModule;

public class App extends Application implements IApp {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public App getApplication() {
        return this;
    }

}