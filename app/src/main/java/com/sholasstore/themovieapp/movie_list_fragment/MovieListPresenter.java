package com.sholasstore.themovieapp.movie_list_fragment;

import com.sholasstore.themovieapp.di.MovieListScope;
import com.sholasstore.themovieapp.repo.LocalRepo;
import com.sholasstore.themovieapp.repo.RemoteRepo;
import com.sholasstore.themovieapp.room.MovieListDbModel;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@MovieListScope
public class MovieListPresenter implements MovieListContract.Presenter {
    private RemoteRepo mRemoteRepo;
    private LocalRepo mLocalRepo;
    private MovieListContract.View mView;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    MovieListPresenter(RemoteRepo remoteRepo, LocalRepo localRepo) {
        mRemoteRepo = remoteRepo;
        mLocalRepo = localRepo;
    }

    @Override
    public void fetchData() {
        mView.showLoading();

        mDisposable.add(mLocalRepo.getPopularMovies(MovieListDbFlag.POPULAR_MOVIES)
                .mergeWith(mLocalRepo.getTopMovies(MovieListDbFlag.TOP_MOVIES))
                .mergeWith(mLocalRepo.getUpcomingMovies(MovieListDbFlag.UPCOMING_MOVIES))
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
                    public void accept(List<MovieListUIModel> movieListUIModels) throws Exception {
                        mView.submitList(movieListUIModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                }));


    }

    @Override
    public void refresh() {
        mDisposable.add(mRemoteRepo.getPopularMovies(1)
                .mergeWith(mRemoteRepo.getTopRatedMovies(1))
                .mergeWith(mRemoteRepo.getUpcomingMovies(1))
                .subscribe());
    }

    @Override
    public void attachView(MovieListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mRemoteRepo = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}