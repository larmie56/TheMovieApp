package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.main_activity.MainActivityContract;
import com.sholasstore.themovieapp.main_activity.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @Singleton
    public MainActivityContract.Presenter providesPresenter() {
        return new MainActivityPresenter();
    }
}
