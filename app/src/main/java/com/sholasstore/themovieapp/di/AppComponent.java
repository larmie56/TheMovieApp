package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.main_activity.MainActivity;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsFragment;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, MainActivityModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void injectIntoMovieListFragment(MovieListFragment movieListFragment);
    void injectIntoMovieDetailsFragment(MovieDetailsFragment movieDetailsFragment);
}