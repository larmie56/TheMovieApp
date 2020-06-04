package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

// TODO: 6/3/2020 Provide a complete implementation for this class and incorporate the local data source into the repository layer
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