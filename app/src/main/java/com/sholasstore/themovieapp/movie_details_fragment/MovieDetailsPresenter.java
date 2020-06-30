package com.sholasstore.themovieapp.movie_details_fragment;

import com.sholasstore.themovieapp.repo.LocalRepoImpl;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    private MovieDetailsContract.View mView;
    private LocalRepoImpl mLocalRepo;
    private RemoteRepoImpl mRemoteRepo;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    MovieDetailsPresenter(RemoteRepoImpl remoteRepo, LocalRepoImpl localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    @Override
    public void fetchData(int movieId) {
        mView.showLoading();

        mDisposable.add(mLocalRepo.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Consumer<MovieDetailsUIModel>() {
                    @Override
                    public void accept(MovieDetailsUIModel movieDetailsUIModel) throws Exception {
                        mView.showMovieDetails(movieDetailsUIModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                }));
    }

    @Override
    public void refresh(int movieId) {
        mDisposable.add(mRemoteRepo.getMovieDetails(movieId)
                .subscribe());
    }

    @Override
    public void attachView(MovieDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
