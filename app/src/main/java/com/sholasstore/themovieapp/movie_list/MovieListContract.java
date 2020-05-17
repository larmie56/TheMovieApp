package com.sholasstore.themovieapp.movie_list;

import java.util.List;

public interface MovieListContract {

    interface View extends BaseView {
        void submitList(List<MovieListUIModel> uiModel);
        void showData();
        void showLoading();
        void hideLoading();
        void showError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
