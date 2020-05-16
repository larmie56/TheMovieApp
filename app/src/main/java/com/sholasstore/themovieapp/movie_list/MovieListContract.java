package com.sholasstore.themovieapp.movie_list;

import java.util.List;

public interface MovieListContract {

    interface View extends BaseView {
        void showData(List<MovieListUIModel> uiModels);
        void showLoading();
        void hideLoading();
        void showError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
