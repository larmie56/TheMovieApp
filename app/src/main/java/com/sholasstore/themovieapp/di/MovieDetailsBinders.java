package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsContract;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieDetailsBinders {

    @Binds
    public abstract MovieDetailsContract.Presenter bindsPresenter(MovieDetailsPresenter presenter);
}
