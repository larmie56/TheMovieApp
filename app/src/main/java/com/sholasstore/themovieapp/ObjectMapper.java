package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.MovieResponse;
import com.sholasstore.themovieapp.model.MovieResult;
import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public static List<MovieListUIModel> mapApiResponseToUIModel(MovieResponse response) {
        List<MovieListUIModel> uiModels = new ArrayList<>();

        for (MovieResult result: response.getResults()) {
            uiModels.add(new MovieListUIModel(result.getPoster_path(), result.getTitle()));
        }

        return uiModels;
    }
}
