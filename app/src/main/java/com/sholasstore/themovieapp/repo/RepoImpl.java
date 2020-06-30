/*
package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;
import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


// TODO: 6/3/2020 Provide a complete implementation for this class and incorporate the local data source into the repository layer
public class RepoImpl {

    private LocalRepo mLocalRepo;
    private RemoteRepo mRemoteRepo;

    @Inject
    public RepoImpl(RemoteRepoImpl remoteRepo, LocalRepoImpl localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    public Single<List<MovieListDbModel>> getPopularMovies() {

    }

    public Flowable<List<MovieListUIModel>> getTopMovies() {
        return mLocalRepo.getTopMovies(MovieListDbFlag.TOP_MOVIES)
                .flat
    }

    public Single<List<MovieListUIModel>> getUpcomingMovies() {
        return mLocalRepo.getTopMovies()
                .onErrorResumeNext(mRemoteRepo.getUpcomingMovies(1))
                .doOnSuccess(new Consumer<List<MovieListDbModel>>() {
                    @Override
                    public void accept(List<MovieListDbModel> movieListDbModels) throws Exception {
                        mLocalRepo.insertMovieList(movieListDbModels);
                    }
                });
    }

    public MovieDetailsUIModel getMovieDetails(int movieId) {
        return mLocalRepo.getMovieDetails(movieId);
    }
}*/
