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
    public void openDetailsFragment() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new MovieDetailsFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}