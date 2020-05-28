package com.sholasstore.themovieapp.movie_list_fragment;

import com.sholasstore.themovieapp.base_mvp.BasePresenter;
import com.sholasstore.themovieapp.base_mvp.BaseView;

import java.util.List;

public interface MovieListContract {

    interface View extends BaseView {
        void submitList(List<MovieListUIModel> uiModel);
        void showData();
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
