package com.sholasstore.themovieapp.movie_list;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
