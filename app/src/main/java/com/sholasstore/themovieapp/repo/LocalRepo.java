package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import java.util.List;

import io.reactivex.Single;

public interface LocalRepo {

    Single<List<MovieListUIModel>> getPopularMovies();
    Single<List<MovieListUIModel>> getTopMovies();
    Single<List<MovieListUIModel>> getUpcomingMovies();
    MovieDetailsUIModel getMovieDetails(int movieId);
    void insertMovieList(List<MovieListUIModel> movieListUIModels);
    void insertMovieDetails(MovieDetailsUIModel movieDetailsUIModel);

}