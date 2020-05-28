package com.sholasstore.themovieapp.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sholasstore.themovieapp.App;
import com.sholasstore.themovieapp.R;
import com.sholasstore.themovieapp.di.AppComponent;
import com.sholasstore.themovieapp.movie_details.MovieDetailsFragment;
import com.sholasstore.themovieapp.movie_list.MovieListFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainActivity, MainActivityContract.View {

    @Inject MainActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        AppComponent appComponent = app.getAppComponent();

        appComponent.inject(this);

        mPresenter.attachView(this);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MovieListFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void recyclerRowItemClicked(int movieId) {
        mPresenter.recyclerRowItemClicked(movieId);
    }

    @Override
    public void openMovieDetailsFragment(int movieId) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        final MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailsFragment.MOVIE_ID_EXTRA, movieId);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}