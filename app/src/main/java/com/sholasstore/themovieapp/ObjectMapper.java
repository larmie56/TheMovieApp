package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.movie_details.MovieGenre;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieResult;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;
import com.sholasstore.themovieapp.room.MovieDetailsDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public static List<MovieListUIModel> mapMovieListDbModelToUIModel(List<MovieListDbModel> dbModels) {
        List<MovieListUIModel> uiModels = new ArrayList<>();

        for (MovieListDbModel dbModel: dbModels) {
            uiModels.add(new MovieListUIModel(dbModel.getPosterPath(), dbModel.getTitle(), dbModel.getId()));
        }

        return uiModels;
    }

    public static MovieDetailsUIModel mapMovieDetailsDbModelToUIModel(MovieDetailsDbModel dbModel) {

        return new MovieDetailsUIModel(dbModel.getPosterPath(),
                dbModel.getTitle(),
                dbModel.getOverview(),
                dbModel.getGenres(),
                dbModel.getReleaseDate(),
                dbModel.getRevenue(),
                dbModel.getRuntime());
    }

    public static List<MovieListDbModel> mapPopularMoviesResponseToDbModel(MovieListResponse response) {
        List<MovieListDbModel> dbModels = new ArrayList<>();

        for (MovieResult result: response.getResults()) {
            dbModels.add(new MovieListDbModel(result.getId(),
                            result.getTitle(),
                            result.getOverview(),
                            result.getPosterPath(),
                    MovieListDbFlag.POPULAR_MOVIES));
        }

        return dbModels;

    }

    public static List<MovieListDbModel> mapTopMoviesResponseToDbModel(MovieListResponse response) {
        List<MovieListDbModel> dbModels = new ArrayList<>();

        for (MovieResult result: response.getResults()) {
            dbModels.add(new MovieListDbModel(result.getId(),
                    result.getTitle(),
                    result.getOverview(),
                    result.getPosterPath(),
                    MovieListDbFlag.TOP_MOVIES));
        }

        return dbModels;

    }

    public static List<MovieListDbModel> mapUpcomingMoviesResponseToDbModel(MovieListResponse response) {
        List<MovieListDbModel> dbModels = new ArrayList<>();

        for (MovieResult result: response.getResults()) {
            dbModels.add(new MovieListDbModel(result.getId(),
                    result.getTitle(),
                    result.getOverview(),
                    result.getPosterPath(),
                    MovieListDbFlag.UPCOMING_MOVIES));
        }

        return dbModels;

    }

    public static MovieDetailsDbModel mapMovieDetailsResponseToDbModel(MovieDetailsResponse response) {
        List<String> genres = new ArrayList<>();
        for (MovieGenre movieGenre: response.getGenres()) {
            genres.add(movieGenre.getName());
        }

        return new MovieDetailsDbModel(response.getId(), response.getTitle(),
                response.getOverview(),
                response.getReleaseDate(),
                response.getRevenue(),
                response.getRuntime(),
                response.getPosterPath(),
                StringUtil.formatMovieGenre(genres));
    }
}