package com.sholasstore.themovieapp.repo;

import android.util.Log;

import com.sholasstore.themovieapp.MovieService;
import com.sholasstore.themovieapp.ObjectMapper;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.room.MovieDetailsDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RemoteRepoImpl implements RemoteRepo {
    private MovieService mMovieService;
    private LocalRepo mLocalRepo;
    private String clazz = this.getClass().getSimpleName();

    @Inject
    public RemoteRepoImpl(MovieService movieService, LocalRepo localRepo) {
        mMovieService = movieService;
        mLocalRepo = localRepo;
    }

    @Override
    public Single<List<MovieListDbModel>> getPopularMovies(int page) {
        return mMovieService.getPopularMovies(page)
                .map(new Function<MovieListResponse, List<MovieListDbModel>>() {
                    @Override
                    public List<MovieListDbModel> apply(MovieListResponse response) {
                        return ObjectMapper.mapPopularMoviesResponseToDbModel(response);
                    }
                }).doOnSuccess(new Consumer<List<MovieListDbModel>>() {
                    @Override
                    public void accept(List<MovieListDbModel> dbModels) throws Exception {
                        Log.d(clazz, "LOG - Inserting popular movies into local database");
                        mLocalRepo.clearMovieList();
                        mLocalRepo.insertMovieList(dbModels);
                    }
                }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<MovieListDbModel>> getTopRatedMovies(int page) {
        return mMovieService.getTopRatedMovies(page)
                .map(new Function<MovieListResponse, List<MovieListDbModel>>() {
                    @Override
                    public List<MovieListDbModel> apply(MovieListResponse response) {
                        return ObjectMapper.mapTopMoviesResponseToDbModel(response);
                    }
                }).doOnSuccess(new Consumer<List<MovieListDbModel>>() {
                    @Override
                    public void accept(List<MovieListDbModel> movieListDbModels) throws Exception {
                        Log.d(clazz, "LOG - Inserting top rated movies into local database");
                        mLocalRepo.clearMovieList();
                        mLocalRepo.insertMovieList(movieListDbModels);
                    }
                }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<MovieListDbModel>> getUpcomingMovies(int page) {
        return mMovieService.getUpcomingMovies(page)
                .map(new Function<MovieListResponse, List<MovieListDbModel>>() {
                    @Override
                    public List<MovieListDbModel> apply(MovieListResponse response) {
                        return ObjectMapper.mapUpcomingMoviesResponseToDbModel(response);
                    }
                }).doOnSuccess(new Consumer<List<MovieListDbModel>>() {
                    @Override
                    public void accept(List<MovieListDbModel> movieListDbModels) throws Exception {
                        Log.d(clazz, "LOG - Inserting upcoming movies into local database");
                        mLocalRepo.clearMovieList();
                        mLocalRepo.insertMovieList(movieListDbModels);
                    }
                }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<MovieDetailsDbModel> getMovieDetails(int movieId) {
        return mMovieService.getMovieDetails(movieId)
                .map(new Function<MovieDetailsResponse, MovieDetailsDbModel>() {
                    @Override
                    public MovieDetailsDbModel apply(MovieDetailsResponse response) throws Exception {
                        return ObjectMapper.mapMovieDetailsResponseToDbModel(response);
                    }
                }).doOnSuccess(new Consumer<MovieDetailsDbModel>() {
                    @Override
                    public void accept(MovieDetailsDbModel movieDetailsDbModel) throws Exception {
                        mLocalRepo.insertMovieDetails(movieDetailsDbModel);
                    }
                }).subscribeOn(Schedulers.io());
    }
}