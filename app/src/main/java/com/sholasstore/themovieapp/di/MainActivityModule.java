package com.sholasstore.themovieapp.di;

import android.content.Context;

import com.sholasstore.themovieapp.main_activity.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mMainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Provides
    public Context providesContext() {
        return mMainActivity;
    }
}
