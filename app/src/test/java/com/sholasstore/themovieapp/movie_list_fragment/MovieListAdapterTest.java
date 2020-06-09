package com.sholasstore.themovieapp.movie_list_fragment;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.view.ContextThemeWrapper;

import com.sholasstore.themovieapp.R;
import com.sholasstore.themovieapp.main_activity.IMainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import androidx.test.core.app.ApplicationProvider;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MovieListAdapterTest {

    @Mock IMainActivity mIMainActivity;
    private MovieListAdapter mMovieListAdapter;
    private ContextThemeWrapper mContextThemeWrapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Context context = ApplicationProvider.getApplicationContext();
        mContextThemeWrapper = new ContextThemeWrapper(context, R.style.AppTheme);

        mMovieListAdapter = new MovieListAdapter(null, mContextThemeWrapper, mIMainActivity);
    }

    @Test
    public void onCreateViewHolderTest() {
        LinearLayout linearLayout = new LinearLayout(mContextThemeWrapper);
        MovieListAdapter.MovieItemViewHolder movieItemViewHolder =
                mMovieListAdapter.onCreateViewHolder(linearLayout, R.layout.fragment_movie_list);

        assertNotNull(movieItemViewHolder);
        assertTrue(movieItemViewHolder.mItemBinding.getRoot().findViewById(R.id.imageView_moviePoster)
                instanceof ImageView);
    }

    @Test
    public void onBindViewHolderTest() {
        LinearLayout linearLayout = new LinearLayout(mContextThemeWrapper);
        MovieListAdapter.MovieItemViewHolder movieItemViewHolder =
                mMovieListAdapter.onCreateViewHolder(linearLayout, R.layout.fragment_movie_list);

        List<MovieListUIModel> uiModels = getUIModels();
        mMovieListAdapter.setUIModels(uiModels);

        mMovieListAdapter.onBindViewHolder(movieItemViewHolder, 0);
        movieItemViewHolder.itemView.performClick();

        verify(mIMainActivity).recyclerRowItemClicked(1);

        mMovieListAdapter.onBindViewHolder(movieItemViewHolder, 1);
        movieItemViewHolder.itemView.performClick();

        verify(mIMainActivity).recyclerRowItemClicked(2);

    }

    @Test
    public void getItemCountTest() {
        List<MovieListUIModel> uiModels = getUIModels();

        mMovieListAdapter.setUIModels(uiModels);

        int actualItemCount = mMovieListAdapter.getItemCount();

        assertTrue(getUIModels().size() == actualItemCount);
    }


    private List<MovieListUIModel> getUIModels() {
        List<MovieListUIModel> uiModels = new ArrayList<>();

        MovieListUIModel adAstra = new MovieListUIModel("", "Ad astra", 1);
        MovieListUIModel scoobyDoo = new MovieListUIModel("", "Scooby-Doo", 2);
        MovieListUIModel theMartian = new MovieListUIModel("", "The Martian", 3);

        uiModels.add(adAstra);
        uiModels.add(scoobyDoo);
        uiModels.add(theMartian);

        return uiModels;
    }
}