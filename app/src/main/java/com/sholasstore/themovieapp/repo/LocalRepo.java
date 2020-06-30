package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;
import com.sholasstore.themovieapp.room.MovieDetailsDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.List;

import io.reactivex.Flowable;

public interface LocalRepo {

    Flowable<List<MovieListUIModel>> getPopularMovies(MovieListDbFlag flag);
    Flowable<List<MovieListUIModel>> getTopMovies(MovieListDbFlag flag);
    Flowable<List<MovieListUIModel>> getUpcomingMovies(MovieListDbFlag flag);
    Flowable<MovieDetailsUIModel> getMovieDetails(int movieId);
    void insertMovieList(List<MovieListDbModel> movieListUIModels);
    void insertMovieDetails(MovieDetailsDbModel movieDetailsUIModel);
    void clearMovieList();
    void clearMovieDetails();

}