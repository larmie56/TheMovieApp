package com.sholasstore.themovieapp.movie_list_fragment;

public class MovieListUIModel {

    private int movieId;
    private String posterPath;
    private String title;

    public MovieListUIModel(String posterPath, String title, int movieId) {
        this.posterPath = posterPath;
        this.title = title;
        this.movieId = movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public int getMovieId() {
        return movieId;
    }
}
