package com.sholasstore.themovieapp.base_mvp;

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(Throwable throwable);
}