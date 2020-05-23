package com.sholasstore.themovieapp.movie_details;

import com.sholasstore.themovieapp.movie_list.MovieListContract;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    private MovieDetailsContract.View mView;

    MovieDetailsPresenter(MovieDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void fetchData(int movieId) {

    }

    @Override
    public void attachView(MovieDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
