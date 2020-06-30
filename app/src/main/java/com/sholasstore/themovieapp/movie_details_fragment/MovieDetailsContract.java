package com.sholasstore.themovieapp.movie_details_fragment;

import com.sholasstore.themovieapp.base_mvp.BasePresenter;
import com.sholasstore.themovieapp.base_mvp.BaseView;

public interface MovieDetailsContract {

    interface View extends BaseView {
        void showMovieDetails(MovieDetailsUIModel uiModel);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData(int movieId);
        void refresh(int movieId);
    }
}
