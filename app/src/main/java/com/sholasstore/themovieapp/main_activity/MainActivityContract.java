package com.sholasstore.themovieapp.main_activity;

public class MainActivityContract {

    public interface View {
        void openMovieDetailsFragment(int movieId);
    }

    public interface Presenter {
        void attachView(View view);
        void detachView();
        void recyclerRowItemClicked(int movieId);
    }
}
