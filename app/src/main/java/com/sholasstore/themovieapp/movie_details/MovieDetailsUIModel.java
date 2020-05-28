package com.sholasstore.themovieapp.movie_details;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.sholasstore.themovieapp.room.MovieGenreTypeConverter;

import java.util.List;

//Database Entity Class
@Entity(tableName = "movie_details")
public class MovieDetailsUIModel {

    private String posterPath;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_title")
    private String movieTitle;
    @ColumnInfo(name = "movie_overview")
    private String movieOverview;
    @ColumnInfo(name = "movie_genres")
    @TypeConverters(value = {MovieGenreTypeConverter.class})
    private List<String> movieGenres;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @ColumnInfo(name = "_revenue")
    private int revenue;
    @ColumnInfo(name = "_runtime")
    private int  runtime;
    @ColumnInfo(name = "movie_id")
    private int movieId;

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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}