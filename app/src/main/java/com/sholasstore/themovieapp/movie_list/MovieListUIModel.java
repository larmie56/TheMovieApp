package com.sholasstore.themovieapp.movie_list;

public class MovieListUIModel {

    private String posterPath;
    private String title;

    public MovieListUIModel(String posterPath, String title) {
        this.posterPath = posterPath;
        this.title = title;
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
}
