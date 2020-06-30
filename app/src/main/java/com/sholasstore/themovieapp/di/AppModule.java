package com.sholasstore.themovieapp.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sholasstore.themovieapp.room.MovieDao;
import com.sholasstore.themovieapp.room.MovieDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public MovieDao providesMovieDao() {
        MovieDatabase movieDatabase =
                Room.databaseBuilder(providesContext(), MovieDatabase.class, MovieDatabase.DATABASE_NAME).build();

        return movieDatabase.getMovieDao();
    }
}
