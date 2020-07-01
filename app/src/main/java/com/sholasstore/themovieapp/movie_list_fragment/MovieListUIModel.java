package com.sholasstore.themovieapp.movie_list_fragment;

import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

public class MovieListUIModel {

    private int movieId;
    private String posterPath;
    private String title;
    private MovieListDbFlag mFlag;

    public MovieListUIModel(String posterPath, String title, int movieId, MovieListDbFlag flag) {
        this.posterPath = posterPath;
        this.title = title;
        this.movieId = movieId;
        this.mFlag = flag;
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

    public MovieListDbFlag getFlag() {
        return mFlag;
    }
}
