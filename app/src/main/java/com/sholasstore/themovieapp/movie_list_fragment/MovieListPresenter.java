package com.sholasstore.themovieapp.movie_list_fragment;

import android.util.Log;

import com.sholasstore.themovieapp.di.MovieListScope;
import com.sholasstore.themovieapp.repo.LocalRepo;
import com.sholasstore.themovieapp.repo.RemoteRepo;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@MovieListScope
public class MovieListPresenter implements MovieListContract.Presenter {
    private RemoteRepo mRemoteRepo;
    private LocalRepo mLocalRepo;
    private MovieListContract.View mView;
    private Disposable mLocalDisposable;
    private Disposable mRemoteDisposable;
    private String clazz = this.getClass().getSimpleName();

    @Inject
    MovieListPresenter(RemoteRepo remoteRepo, LocalRepo localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    @Override
    public void fetchData() {
        Log.d(clazz, "LOG - Fetching data from local repo");
        mView.showLoading();

        mLocalDisposable = mLocalRepo.getPopularMovies(MovieListDbFlag.POPULAR_MOVIES).subscribeOn(Schedulers.io())
                .mergeWith(mLocalRepo.getTopMovies(MovieListDbFlag.TOP_MOVIES)).subscribeOn(Schedulers.io())
                .mergeWith(mLocalRepo.getUpcomingMovies(MovieListDbFlag.UPCOMING_MOVIES))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                        mView.showData();
                    }
                })
                .subscribe(new Consumer<List<MovieListUIModel>>() {
                    @Override
                    public void accept(List<MovieListUIModel> movieListUIModels) throws Exception {
                        Log.d(clazz, "LOG - Submitting list to the view");
                        mView.submitList(movieListUIModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                });
    }

    @Override
    public void refresh() {
        Log.d(clazz, "LOG - Fetching from network");
        if (mLocalDisposable != null && !mLocalDisposable.isDisposed()) {
            Log.d(clazz, "LOG - cancelling call to local database subscription");
            mLocalDisposable.dispose();
        }

        mRemoteDisposable = mRemoteRepo.getPopularMovies(1).subscribeOn(Schedulers.io())
                .mergeWith(mRemoteRepo.getTopRatedMovies(1)).subscribeOn(Schedulers.io())
                .mergeWith(mRemoteRepo.getUpcomingMovies(1)).subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(clazz, "LOG - Fetching from network done, calling fetch from local");
                        fetchData();
                    }
                })
                .subscribe();
    }

    @Override
    public void attachView(MovieListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mRemoteRepo = null;
        mLocalRepo = null;
        if (mLocalDisposable != null && !mLocalDisposable.isDisposed()) {
            mLocalDisposable.dispose();
        }
        if (mRemoteDisposable != null && !mRemoteDisposable.isDisposed()) {
            mLocalDisposable.dispose();
        }
    }
}