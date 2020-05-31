package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_list_fragment.MovieListFragment;

import dagger.Subcomponent;

@MovieListScope
@Subcomponent(modules = {MovieListBinders.class})
public interface MovieListComponent {

    void inject(MovieListFragment movieListFragment);
}
