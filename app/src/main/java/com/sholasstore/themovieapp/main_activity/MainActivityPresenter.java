package com.sholasstore.themovieapp.main_activity;

import javax.inject.Inject;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mView;

    @Inject
    public MainActivityPresenter(){}

    @Override
    public void attachView(MainActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void recyclerRowItemClicked(int movieId) {
        mView.openMovieDetailsFragment(movieId);
    }
}
