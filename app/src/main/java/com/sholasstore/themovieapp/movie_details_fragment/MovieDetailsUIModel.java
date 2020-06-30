package com.sholasstore.themovieapp.movie_details_fragment;

import androidx.room.TypeConverters;

import com.sholasstore.themovieapp.room.MovieGenreTypeConverter;

import java.util.List;

public class MovieDetailsUIModel {

    private String posterPath;
    private String movieTitle;
    private String movieOverview;
    private String movieGenres;
    private String releaseDate;
    private int revenue;
    private int  runtime;

    public MovieDetailsUIModel(String posterPath, String movieTitle, String movieOverview, String movieGenres, String releaseDate, int revenue, int runtime) {
        this.posterPath = posterPath;
        this.movieTitle = movieTitle;
        this.movieOverview = movieOverview;
        this.movieGenres = movieGenres;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMovieGenres() {
        return movieGenres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

}