package com.sholasstore.themovieapp.movie_list_fragment;

import com.sholasstore.themovieapp.di.MovieListScope;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@MovieListScope
public class MovieListPresenter implements MovieListContract.Presenter {
    private RemoteRepoImpl mRepo;
    private MovieListContract.View mView;
    private Disposable mDisposable;

    @Inject
    MovieListPresenter(RemoteRepoImpl repo) {
        mRepo = repo;
    }

    @Override
    public void fetchData() {

        mView.showLoading();

        //Calling subscribeOn() once makes all the api get calls on a single background thread
        //Call subscribeOn() after each api get call to make each api get call on different background threads
        mDisposable = mRepo.getPopularMovies(1)
                .mergeWith(mRepo.getTopRatedMovies(1))
                .mergeWith(mRepo.getUpcomingMovies(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                        mView.showData();
                    }
                }).subscribe(new Consumer<List<MovieListUIModel>>() {
                    @Override
                    public void accept(List<MovieListUIModel> uiModels) throws Exception {
                        mView.submitList(uiModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                });

    }

    @Override
    public void attachView(MovieListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mRepo = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}