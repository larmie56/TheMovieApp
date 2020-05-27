package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.MainActivity;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}