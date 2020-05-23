package com.sholasstore.themovieapp.base_mvp;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
