package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.List;

import io.reactivex.Single;

interface RemoteRepo {

    Single<List<MovieListUIModel>> getPopularMovies(int page);
    Single<List<MovieListUIModel>> getTopRatedMovies(int page);
    Single<List<MovieListUIModel>> getUpcomingMovies(int page);
    Single<MovieDetailsUIModel> getMovieDetails(int movieId);
}
