package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.room.MovieDetailsDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;

import java.util.List;

import io.reactivex.Single;

public interface RemoteRepo {

    Single<List<MovieListDbModel>> getPopularMovies(int page);
    Single<List<MovieListDbModel>> getTopRatedMovies(int page);
    Single<List<MovieListDbModel>> getUpcomingMovies(int page);
    Single<MovieDetailsDbModel> getMovieDetails(int movieId);
}