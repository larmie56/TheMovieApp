package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;
import com.sholasstore.themovieapp.room.MovieDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalRepoImpl implements LocalRepo {

    private MovieDao mMovieDao;

    @Inject
    public LocalRepoImpl(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    public Single<List<MovieListUIModel>> getPopularMovies() {
        return mMovieDao.getPopularMovieList();
    }

    @Override
    public Single<List<MovieListUIModel>> getTopMovies() {
        return mMovieDao.getTopMovieList();
    }

    @Override
    public Single<List<MovieListUIModel>> getUpcomingMovies() {
        return mMovieDao.getUpcomingMovieList();
    }

    @Override
    public MovieDetailsUIModel getMovieDetails(int movieId) {
        return mMovieDao.getMovieDetails(movieId);
    }

    @Override
    public void insertMovieList(List<MovieListUIModel> movieListUIModels) {
        mMovieDao.insertMovieList(movieListUIModels);
    }

    @Override
    public void insertMovieDetails(MovieDetailsUIModel movieDetailsUIModel) {
        mMovieDao.insertMovieDetails(movieDetailsUIModel);
    }
}
