package com.sholasstore.themovieapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_list WHERE _flag = :flag LIMIT 30")
    Flowable<List<MovieListDbModel>> getPopularMovieList(String flag);

    @Query("SELECT * FROM movie_list WHERE _flag = :flag LIMIT 30")
    Flowable<List<MovieListDbModel>> getTopMovieList(String flag);

    @Query("SELECT * FROM movie_list WHERE _flag = :flag LIMIT 30")
    Flowable<List<MovieListDbModel>> getUpcomingMovieList(String flag);

    @Query("SELECT * FROM movie_details WHERE movie_id = :movieId LIMIT 1 ")
    MovieDetailsDbModel getMovieDetails(int movieId);

    @Insert(entity = MovieListDbModel.class, onConflict = OnConflictStrategy.REPLACE)
    void insertMovieList(List<MovieListDbModel> movieListDbModels);

    @Insert(entity = MovieDetailsDbModel.class, onConflict = OnConflictStrategy.REPLACE)
    void insertMovieDetails(MovieDetailsDbModel movieDetailsUIModel);

    @Query("DELETE FROM movie_list")
    void clearMovieList();

    @Query("DELETE FROM movie_details")
    void clearMovieDetails();
}
