package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsFragment;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    MainActivityComponent plusMainActivity(MainActivityModule mainActivityModule);
    MovieListComponent plusMovieList();
    MovieDetailsComponent plusMovieDetails();
}