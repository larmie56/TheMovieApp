package com.sholasstore.themovieapp.movie_list_fragment;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sholasstore.themovieapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MovieListFragmentTest {

    private FragmentScenario<MovieListFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario
                = FragmentScenario.launchInContainer(MovieListFragment.class);
    }

    @Test
    public void popularMoviesTextView_containsCorrectText() {
        onView(withId(R.id.textView_popularMovies))
                .check(matches(withText(R.string.popular_movies)));

    }

    @Test
    public void topMoviesTextView_containsCorrectText() {
        onView(withId(R.id.textView_topMovies))
                .check(matches(withText(R.string.top_movies)));
    }

    @Test
    public void upcomingMoviesTextView_containsCorrectText() {
        onView(withId(R.id.textView_upcomingMovies))
                .check(matches(withText(R.string.upcoming_movies)));
    }

    @Test
    public void progressBar_isVisible() {
        onView(withId(R.id.progressCircular))
                .check(matches(isDisplayed()));
    }

    @Test
    public void popularMoviesRecyclerView_isWellPositioned() {
        onView(withId(R.id.recyclerView_popularMovies))
                .check(isCompletelyBelow(withId(R.id.textView_popularMovies)))
                .check(isCompletelyAbove(withId(R.id.textView_topMovies)));
    }

    @Test
    public void topMoviesRecyclerView_isWellPositioned() {
        onView(withId(R.id.recyclerView_topMovies))
                .check(isCompletelyBelow(withId(R.id.textView_topMovies)))
                .check(isCompletelyAbove(withId(R.id.textView_upcomingMovies)));
    }

    @Test
    public void upcomingMoviesRecyclerView_isWellPositioned() {
        onView(withId(R.id.recyclerView_upcomingMovies))
                .check(isCompletelyBelow(withId(R.id.textView_upcomingMovies)));
    }


}