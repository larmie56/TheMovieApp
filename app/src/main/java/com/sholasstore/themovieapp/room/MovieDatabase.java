package com.sholasstore.themovieapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {MovieListDbModel.class, MovieDetailsDbModel.class}, exportSchema = false, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MovieApp.db";

    public abstract MovieDao getMovieDao();
}