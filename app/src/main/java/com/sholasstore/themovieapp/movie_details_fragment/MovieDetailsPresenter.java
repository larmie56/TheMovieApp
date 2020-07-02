package com.sholasstore.themovieapp.movie_details_fragment;

import android.os.Handler;
import android.os.Looper;

import com.sholasstore.themovieapp.repo.LocalRepo;
import com.sholasstore.themovieapp.repo.LocalRepoImpl;
import com.sholasstore.themovieapp.repo.RemoteRepo;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    private MovieDetailsContract.View mView;
    private LocalRepo mLocalRepo;
    private RemoteRepo mRemoteRepo;
    private Disposable mLocalDisposable;
    private Disposable mRemoteDisposable;

    @Inject
    MovieDetailsPresenter(RemoteRepoImpl remoteRepo, LocalRepoImpl localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    /*@Override
    public void fetchData(final int movieId) {
        mView.showLoading();

        mLocalDisposable = mLocalRepo.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Consumer<MovieDetailsUIModel>() {
                    @Override
                    public void accept(MovieDetailsUIModel movieDetailsUIModel) throws Exception {
                            mView.showMovieDetails(movieDetailsUIModel);
                            mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                });
    }*/

    @Override
    public void fetchData(final int movieId) {
        mView.showLoading();

        //Fetching from the local database using a regular java Thread for creating the background thread
        // and a/an Handler for switching back to the main thread.

        //TODO: 2/7/2020 - Rework the method of performing the asynchronous queries, especially usage of Handler.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final MovieDetailsUIModel uiModel = mLocalRepo.getMovieDetails(movieId);
                if (uiModel == null)
                    refresh(movieId);
                else {
                    Handler handler = new android.os.Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mView.showMovieDetails(uiModel);
                            mView.hideLoading();
                        }
                    });
                }
            }
        });

        thread.start();
    }

    @Override
    public void refresh(final int movieId) {
        mRemoteDisposable = mRemoteRepo.getMovieDetails(movieId)
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        fetchData(movieId);
                    }
                })
                .subscribe();
    }

    @Override
    public void attachView(MovieDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        if (mLocalDisposable != null && !mLocalDisposable.isDisposed()) {
            mLocalDisposable.dispose();
        }
        if (mRemoteDisposable != null && !mRemoteDisposable.isDisposed()) {
            mRemoteDisposable.dispose();
        }
    }
}
