package com.sholasstore.themovieapp.di;

import android.app.Application;
import android.content.Context;

import com.sholasstore.themovieapp.Service;
import com.sholasstore.themovieapp.repo.RepoImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Context providesContext() {
        return mApplication;
    }

    @Provides
    public RepoImpl provideRepoImpl(Service service) {
        return new RepoImpl(service);
    }
}
