package com.sholasstore.themovieapp.movie_details;

import java.util.List;

public class MovieDetailsUIModel {

    private String posterPath;
    private String movieTitle;
    private String movieOverview;
    private List<String> movieGenres;
    private String releaseDate;
    private int revenue;
    private int  runtime;

    public MovieDetailsUIModel(String posterPath, String movieTitle, String movieOverview, List<String> movieGenres, String releaseDate, int revenue, int runtime) {
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

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public List<String> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(List<String> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}