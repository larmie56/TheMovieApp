package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsFragment;

import dagger.Subcomponent;

@MovieDetailsScope
@Subcomponent(modules = {MovieDetailsBinders.class})
public interface MovieDetailsComponent {

    void inject(MovieDetailsFragment movieDetailsFragment);
}
