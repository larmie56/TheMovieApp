package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_list.MovieListUIModel;

import java.util.List;

import io.reactivex.Single;

interface RemoteRepo {

    Single<List<MovieListUIModel>> getPopularMovies(int page);
}
