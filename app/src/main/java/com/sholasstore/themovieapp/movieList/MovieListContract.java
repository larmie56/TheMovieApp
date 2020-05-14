package com.sholasstore.themovieapp.movieList;

public interface MovieListContract {

    interface View extends BaseView<View> {
        void showData();
        void showLoading();
        void hideLoading();
    }

    interface Presenter {
        void fetchData();
    }
}
