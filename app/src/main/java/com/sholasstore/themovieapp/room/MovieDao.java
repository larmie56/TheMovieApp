package com.sholasstore.themovieapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sholasstore.themovieapp.movie_details.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_list WHERE _flag = 0 LIMIT 30")
    Flowable<List<MovieListUIModel>> getPopularMovieList();

    @Query("SELECT * FROM movie_list WHERE _flag = 1 LIMIT 30")
    Flowable<List<MovieListUIModel>> getTopMovieList();

    @Query("SELECT * FROM movie_list WHERE _flag = 2 LIMIT 30")
    Flowable<List<MovieListUIModel>> getUpcomingMovieList();

    @Query("SELECT * FROM movie_details WHERE movie_id = :movieId LIMIT 1 ")
    MovieDetailsUIModel getMovieDetails(int movieId);

    @Insert(entity = MovieListUIModel.class, onConflict = OnConflictStrategy.REPLACE)
    void insertMovieList(List<MovieListUIModel> movieListUIModels);

    @Insert(entity = MovieDetailsUIModel.class, onConflict = OnConflictStrategy.REPLACE)
    void insertMovieDetails(MovieDetailsUIModel movieDetailsUIModel);

    @Update(entity = MovieListUIModel.class, onConflict = OnConflictStrategy.REPLACE)
    void updateMovieList(List<MovieListUIModel> movieListUIModels);

    @Update(entity = MovieDetailsUIModel.class, onConflict = OnConflictStrategy.REPLACE)
    void updateMovieDetails(MovieDetailsUIModel movieDetailsUIModel);
}
