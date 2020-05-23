package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.ObjectMapper;
import com.sholasstore.themovieapp.Service;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.movie_details.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class RepoImpl implements RemoteRepo {
    private Service mService;

    public RepoImpl(Service service) {
        mService = service;
    }

    @Override
    public Single<List<MovieListUIModel>> getPopularMovies(int page) {
        return mService.getPopularMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<List<MovieListUIModel>> getTopRatedMovies(int page) {
        return mService.getTopRatedMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<List<MovieListUIModel>> getUpcomingMovies(int page) {
        return mService.getUpcomingMovies(page)
                .map(new Function<MovieListResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieListResponse response) {
                return ObjectMapper.mapMovieListResponseToUIModel(response);
            }
        });
    }

    @Override
    public Single<MovieDetailsUIModel> getMovieDetails(int movieId) {
        return mService.getMovieDetails(movieId)
                .map(new Function<MovieDetailsResponse, MovieDetailsUIModel>() {
            @Override
            public MovieDetailsUIModel apply(MovieDetailsResponse response) throws Exception {
                return ObjectMapper.mapMovieDetailsResponseToUImodel(response);
            }
        });
    }
}
