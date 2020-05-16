package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.ObjectMapper;
import com.sholasstore.themovieapp.Service;
import com.sholasstore.themovieapp.model.MovieResponse;
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
        return mService.getPopularMovies(page).map(new Function<MovieResponse, List<MovieListUIModel>>() {
            @Override
            public List<MovieListUIModel> apply(MovieResponse response) {
                return ObjectMapper.mapApiResponseToUIModel(response);
            }
        });
    }
}
