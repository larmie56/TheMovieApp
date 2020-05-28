package com.sholasstore.themovieapp.movie_list;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Database Entity class
@Entity(tableName = "movie_list")
public class MovieListUIModel {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int movieId;
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @ColumnInfo(name = "_title")
    private String title;
    @ColumnInfo(name = "_flag")
    private int flag;

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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
