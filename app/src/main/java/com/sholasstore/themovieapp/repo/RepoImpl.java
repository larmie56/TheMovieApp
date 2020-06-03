package com.sholasstore.themovieapp.repo;

import androidx.annotation.NonNull;

import com.sholasstore.themovieapp.di.MovieDetailsBinders;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;

public class RepoImpl {

    private LocalRepo mLocalRepo;
    private RemoteRepo mRemoteRepo;

    @Inject
    public RepoImpl(RemoteRepoImpl remoteRepo, LocalRepoImpl localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    public Single<List<MovieListUIModel>> getPopularMovies() {
        return mLocalRepo.getPopularMovies()
                .onErrorResumeNext(mRemoteRepo.getPopularMovies(1))
                .doAfterSuccess(new Consumer<List<MovieListUIModel>>() {
                    @Override
                    public void accept(List<MovieListUIModel> movieListUIModels) throws Exception {
                        mLocalRepo.insertMovieList(movieListUIModels);
                    }
                });
    }

    public Single<List<MovieListUIModel>> getTopMovies() {
        return mLocalRepo.getTopMovies()
                .onErrorResumeNext(mRemoteRepo.getTopRatedMovies(1))
                .doOnSuccess(new Consumer<List<MovieListUIModel>>() {
                    @Override
                    public void accept(List<MovieListUIModel> movieListUIModels) throws Exception {
                        mLocalRepo.insertMovieList(movieListUIModels);
                    }
                });
    }

    public Single<List<MovieListUIModel>> getUpcomingMovies() {
        return mLocalRepo.getTopMovies()
                .onErrorResumeNext(mRemoteRepo.getUpcomingMovies(1))
                .doOnSuccess(new Consumer<List<MovieListUIModel>>() {
                    @Override
                    public void accept(List<MovieListUIModel> movieListUIModels) throws Exception {
                        mLocalRepo.insertMovieList(movieListUIModels);
                    }
                });
    }

    public MovieDetailsUIModel getMovieDetails(int movieId) {
        return mLocalRepo.getMovieDetails(movieId);
    }
}