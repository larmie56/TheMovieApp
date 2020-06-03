package com.sholasstore.themovieapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_list WHERE _flag = 0 LIMIT 30")
    Single<List<MovieListUIModel>> getPopularMovieList();

    @Query("SELECT * FROM movie_list WHERE _flag = 1 LIMIT 30")
    Single<List<MovieListUIModel>> getTopMovieList();

    @Query("SELECT * FROM movie_list WHERE _flag = 2 LIMIT 30")
    Single<List<MovieListUIModel>> getUpcomingMovieList();

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
