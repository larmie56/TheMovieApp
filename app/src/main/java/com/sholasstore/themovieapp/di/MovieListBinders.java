package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_list_fragment.MovieListContract;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieListBinders {

    @Binds
    public abstract MovieListContract.Presenter providesPresenter(MovieListPresenter presenter);
}
