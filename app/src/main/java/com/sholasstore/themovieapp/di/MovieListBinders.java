package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_list_fragment.MovieListContract;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListPresenter;
import com.sholasstore.themovieapp.repo.RemoteRepo;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieListBinders {

    @Binds
    public abstract MovieListContract.Presenter providesPresenter(MovieListPresenter presenter);
}
