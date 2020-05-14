package com.sholasstore.themovieapp.movieList;

public interface BaseView<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
