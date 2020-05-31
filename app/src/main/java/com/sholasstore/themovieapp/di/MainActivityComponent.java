package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.main_activity.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {MainActivityModule.class, MainActivityBinders.class})
@MainActivityScope
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}