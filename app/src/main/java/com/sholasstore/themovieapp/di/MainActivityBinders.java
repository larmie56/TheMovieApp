package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.main_activity.MainActivityContract;
import com.sholasstore.themovieapp.main_activity.MainActivityPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainActivityBinders {

    @Binds
    abstract public MainActivityContract.Presenter providesPresentrer(MainActivityPresenter presenter);
}
