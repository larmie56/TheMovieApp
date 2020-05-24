package com.sholasstore.themovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sholasstore.themovieapp.movie_details.MovieDetailsFragment;
import com.sholasstore.themovieapp.movie_list.MovieListFragment;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MovieListFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void openDetailsFragment(int movieId) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        final MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailsFragment.MOVIE_ID_EXTRA, movieId);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}