package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("popular")
    Single<MovieResponse> getPopularMovies(@Query("page") int page);

    @GET("top_rated")
    Single<MovieResponse> getTopRatedMovies(@Query("page") int page);

    @GET("upcoming")
    Single<MovieResponse> getUpcomingMovies(@Query("page") int page);
}
