package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.movie_details.MovieGenre;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieResult;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.movie_details.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public static List<MovieListUIModel> mapMovieListResponseToUIModel(MovieListResponse response) {
        List<MovieListUIModel> uiModels = new ArrayList<>();

        for (MovieResult result: response.getResults()) {
            uiModels.add(new MovieListUIModel(result.getPosterPath(), result.getTitle(), result.getId()));
        }

        return uiModels;
    }

    public static MovieDetailsUIModel mapMovieDetailsResponseToUIModel(MovieDetailsResponse response) {
        List<String> genres = new ArrayList<>();
        for (MovieGenre movieGenre: response.getGenres()) {
            genres.add(movieGenre.getName());
        }

        return new MovieDetailsUIModel(response.getPosterPath(),
                response.getTitle(),
                response.getOverview(),
                genres,
                response.getReleaseDate(),
                response.getRevenue(),
                response.getRuntime());
    }
}