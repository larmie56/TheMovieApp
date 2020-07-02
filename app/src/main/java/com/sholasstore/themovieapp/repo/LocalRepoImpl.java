package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.ObjectMapper;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;
import com.sholasstore.themovieapp.room.MovieDao;
import com.sholasstore.themovieapp.room.MovieDetailsDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class LocalRepoImpl implements LocalRepo {


    private MovieDao mMovieDao;

    @Inject
    public LocalRepoImpl(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    public Maybe<List<MovieListUIModel>> getPopularMovies(MovieListDbFlag flag) {
        return mMovieDao.getPopularMovieList(flag)
                .map(new Function<List<MovieListDbModel>, List<MovieListUIModel>>() {
                    @Override
                    public List<MovieListUIModel> apply(List<MovieListDbModel> movieListDbModels) throws Exception {
                        return ObjectMapper.mapMovieListDbModelToUIModel(movieListDbModels);
                    }
                });
    }

    @Override
    public Maybe<List<MovieListUIModel>> getTopMovies(MovieListDbFlag flag) {
        return mMovieDao.getTopMovieList(flag)
                .map(new Function<List<MovieListDbModel>, List<MovieListUIModel>>() {
                    @Override
                    public List<MovieListUIModel> apply(List<MovieListDbModel> movieListDbModels) throws Exception {
                        return ObjectMapper.mapMovieListDbModelToUIModel(movieListDbModels);
                    }
                });
    }

    @Override
    public Maybe<List<MovieListUIModel>> getUpcomingMovies(MovieListDbFlag flag) {
        return mMovieDao.getUpcomingMovieList(flag)
                .map(new Function<List<MovieListDbModel>, List<MovieListUIModel>>() {
                    @Override
                    public List<MovieListUIModel> apply(List<MovieListDbModel> movieListDbModels) throws Exception {
                        return ObjectMapper.mapMovieListDbModelToUIModel(movieListDbModels);
                    }
                });
    }

    /*@Override
    public Maybe<MovieDetailsUIModel> getMovieDetails(int movieId) {
        return mMovieDao.getMovieDetails(movieId)
                .map(new Function<MovieDetailsDbModel, MovieDetailsUIModel>() {
                    @Override
                    public MovieDetailsUIModel apply(MovieDetailsDbModel movieDetailsDbModel) throws Exception {
                        return ObjectMapper.mapMovieDetailsDbModelToUIModel(movieDetailsDbModel);
                    }
                });
    }*/

    @Override
    public MovieDetailsUIModel getMovieDetails(int movieId) {
        final MovieDetailsDbModel movieDetails = mMovieDao.getMovieDetails(movieId);
        if (movieDetails != null)
            return ObjectMapper.mapMovieDetailsDbModelToUIModel(mMovieDao.getMovieDetails(movieId));
        else
            return null;
    }
    @Override
    public void insertMovieList(List<MovieListDbModel> movieListDbModels) {
        mMovieDao.insertMovieList(movieListDbModels);
    }

    @Override
    public void insertMovieDetails(MovieDetailsDbModel movieDetailsDbModel) {
        mMovieDao.insertMovieDetails(movieDetailsDbModel);
    }

    @Override
    public void clearMovieList() {
        mMovieDao.clearMovieList();
    }

    @Override
    public void clearMovieDetails() {
        mMovieDao.clearMovieDetails();
    }
}