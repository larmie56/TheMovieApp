package com.sholasstore.themovieapp.movie_list;

public class MovieListUIModel {

    private String posterPath;
    private String title;
    private int movieId;

    public MovieListUIModel(String posterPath, String title, int movieId) {
        this.posterPath = posterPath;
        this.title = title;
        this.movieId = movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
