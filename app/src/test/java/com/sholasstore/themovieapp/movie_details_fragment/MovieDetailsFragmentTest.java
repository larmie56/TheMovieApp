package com.sholasstore.themovieapp.movie_details_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.test.core.app.ApplicationProvider;

import com.sholasstore.themovieapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Config(sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MovieDetailsFragmentTest {

    @Mock private MovieDetailsContract.Presenter mPresenter;
    private MovieDetailsFragment mFragment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mFragment = new MovieDetailsFragment();
    }

    @Test
    public void onCreateViewTest() {
        Context context = ApplicationProvider.getApplicationContext();
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(context, R.style.AppTheme);
        LayoutInflater layoutInflater = LayoutInflater.from(themeWrapper);
        LinearLayout linearLayout = new LinearLayout(themeWrapper);
        View view = mFragment.onCreateView(layoutInflater, linearLayout, null);

        assertNotNull(view);
    }

    @Test
    public void onViewCreatedTest() {
        /*final Stubber stubber = doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        });*/
        doNothing().when(mPresenter).fetchData(1);
        Context context = ApplicationProvider.getApplicationContext();
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(context, R.style.AppTheme);
        LayoutInflater layoutInflater = LayoutInflater.from(themeWrapper);
        LinearLayout linearLayout = new LinearLayout(themeWrapper);
        View view = mFragment.onCreateView(layoutInflater, linearLayout, null);

        Bundle bundle = new Bundle();
        bundle.putInt("Movie Id", 1);

        mFragment.onViewCreated(view, bundle);

        verify(mPresenter).attachView(mFragment);
        verify(mPresenter).fetchData(1);
    }

}