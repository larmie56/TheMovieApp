package com.sholasstore.themovieapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_list")
public class MovieListDbModel {

    public enum MovieListDbFlag {
        POPULAR_MOVIES,
        TOP_MOVIES,
        UPCOMING_MOVIES
    }

    public static String POPULAR_MOVIES = "Popular movies";
    public static String TOP_MOVIES = "Top movies";
    public static String UPCOMING_MOVIES = "Upcoming movies";

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private Integer id;
    @ColumnInfo(name = "movie_title")
    private String title;
    @ColumnInfo(name = "movie_overview")
    private String overview;
    @ColumnInfo(name = "movie_poster_path")
    private String posterPath;
    @ColumnInfo(name = "_flag")
    private MovieListDbFlag flag;
    @Ignore
    private String releaseDate;
    @Ignore
    private Double popularity;
    @Ignore
    private Integer voteCount;
    @Ignore
    private Boolean video;
    @Ignore
    private Boolean adult;
    @Ignore
    private String originalLanguage;
    @Ignore
    private String originalTitle;
    @Ignore
    private Double voteAverage;

    public MovieListDbModel(int id, String title, String overview, String posterPath, MovieListDbFlag flag) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.flag = flag;
    }

    public int getId() {return id;}

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {return releaseDate;}

    public void setFlag(MovieListDbFlag flag) {
        this.flag = flag;
    }

    public MovieListDbFlag getFlag() {
        return flag;
    }
}