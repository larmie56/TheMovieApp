package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.MovieService;
import com.sholasstore.themovieapp.ObjectMapper;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class RemoteRepoImpl implements RemoteRepo {
    private MovieService mMovieService;

    @Inject
    public RemoteRepoImpl(MovieService movieService) {
        mMovieService = movieService;
    }

    @Override
    public Single<List<MovieListUIModel>> getPopularMovies(int page) {
        return mMovieService.getPopularMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<List<MovieListUIModel>> getTopRatedMovies(int page) {
        return mMovieService.getTopRatedMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<List<MovieListUIModel>> getUpcomingMovies(int page) {
        return mMovieService.getUpcomingMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<MovieDetailsUIModel> getMovieDetails(int movieId) {
        return mMovieService.getMovieDetails(movieId)
                .map(new Function<MovieDetailsResponse, MovieDetailsUIModel>() {
            @Override
            public MovieDetailsUIModel apply(MovieDetailsResponse response) throws Exception {
                return ObjectMapper.mapMovieDetailsResponseToUIModel(response);
            }
        });
    }
}