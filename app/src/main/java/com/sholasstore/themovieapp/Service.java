package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("popular")
    Single<MovieListResponse> getPopularMovies(@Query("page") int page);

    @GET("top_rated")
    Single<MovieListResponse> getTopRatedMovies(@Query("page") int page);

    @GET("upcoming")
    Single<MovieListResponse> getUpcomingMovies(@Query("page") int page);

    @GET("{movie_id}")
    Single<MovieDetailsResponse> getMovieDetails(@Path("movie_id") int movieId);
}
