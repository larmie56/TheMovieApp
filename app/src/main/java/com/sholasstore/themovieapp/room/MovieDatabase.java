package com.sholasstore.themovieapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

@Database(entities = {MovieListUIModel.class, MovieDetailsUIModel.class}, exportSchema = false, version = 1)
//@TypeConverters(value = {MovieGenreTypeConverter.class})
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MovieApp.db";

    public abstract MovieDao getMovieDao();
}