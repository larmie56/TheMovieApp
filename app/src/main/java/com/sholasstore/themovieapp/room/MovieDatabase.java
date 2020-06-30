package com.sholasstore.themovieapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieListDbModel.class, MovieDetailsDbModel.class}, exportSchema = false, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MovieApp.db";

    public abstract MovieDao getMovieDao();
}