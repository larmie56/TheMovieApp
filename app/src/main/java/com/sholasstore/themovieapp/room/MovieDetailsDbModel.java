package com.sholasstore.themovieapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.sholasstore.themovieapp.model.movie_details.MovieProductionCompany;
import com.sholasstore.themovieapp.model.movie_details.MovieProductionCountry;
import com.sholasstore.themovieapp.model.movie_details.MovieSpokenLanguages;

import java.util.List;

@Entity(tableName = "movie_details")
public class MovieDetailsDbModel {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int id;
    @ColumnInfo(name = "movie_title")
    private String title;
    @ColumnInfo(name = "movie_overview")
    private String overview;
    @ColumnInfo(name = "movie_release_date")
    private String releaseDate;
    @ColumnInfo(name = "movie_revenue")
    private int revenue;
    @ColumnInfo(name = "movie_runtime")
    private int runtime;
    @ColumnInfo(name = "movie_posterpath")
    private String posterPath;
    @ColumnInfo(name = "genres")
    private String genres = null;
    @Ignore
    private Boolean adult;
    @Ignore
    private Integer budget;
    @Ignore
    private String homepage;
    @Ignore
    private String imdbId;
    @Ignore
    private String originalLanguage;
    @Ignore
    private String originalTitle;
    @Ignore
    private Double popularity;
    @Ignore
    private List<MovieProductionCompany> productionCompanies = null;
    @Ignore
    private List<MovieProductionCountry> productionCountries = null;
    @Ignore
    private List<MovieSpokenLanguages> spokenLanguages = null;
    @Ignore
    private String status;
    @Ignore
    private String tagline;
    @Ignore
    private Boolean video;
    @Ignore
    private Float voteAverage;
    @Ignore
    private Integer voteCount;

    public MovieDetailsDbModel(int id, String title, String overview, String releaseDate, int revenue, int runtime, String posterPath, String genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.posterPath = posterPath;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
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

    public String getPosterPath() {
        return posterPath;
    }

    public String getGenres() {
        return genres;
    }
}