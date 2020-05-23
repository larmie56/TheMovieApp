package com.sholasstore.themovieapp.movie_details;

import com.sholasstore.themovieapp.base_mvp.BasePresenter;
import com.sholasstore.themovieapp.base_mvp.BaseView;

public class MovieDetailsContract {

    interface View extends BaseView {
        void showMovieDetails(MovieDetailsUIModel uiModel);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData(int movieId);
    }
}