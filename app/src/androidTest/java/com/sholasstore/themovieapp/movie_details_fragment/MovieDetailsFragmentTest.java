package com.sholasstore.themovieapp.movie_details_fragment;

import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sholasstore.themovieapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsFragmentTest {

    @Before
    public void setup() {
        Bundle bundle = new Bundle();
        bundle.putInt("Movie Id", 574982);
        FragmentScenario<MovieDetailsFragment> fragmentScenario
                = FragmentScenario.launchInContainer(MovieDetailsFragment.class, bundle, R.style.AppTheme, null);


        fragmentScenario.moveToState(Lifecycle.State.RESUMED);

    }

    //TODO: FIX - Every other time this test is run, it runs the test method twice and the second one fails.
    // (i.e. on running the test in succession, the test method will be called twice and the second test method will fail,
    // the next time it is run, it calls it once, as it should, and it passes again, then fails again, and so on)
    // https://stackoverflow.com/questions/58392643/why-does-my-android-unit-test-runs-twice --mentions the exact problem
    @Test
    public void progressBar_isVisible() {

        onView(withId(R.id.progress_circular))
                .check(matches(isDisplayed()));
    }

}