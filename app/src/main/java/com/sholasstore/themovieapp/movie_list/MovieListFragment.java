package com.sholasstore.themovieapp.movie_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sholasstore.themovieapp.App;
import com.sholasstore.themovieapp.databinding.FragmentMovieListBinding;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment implements MovieListContract.View {
    private RecyclerView mPopularMovieRecyclerView;
    private RecyclerView mTopMovieRecyclerView;
    private RecyclerView mUpcomingMovieRecyclerView;
    private FragmentMovieListBinding mBinding;
    private MovieListPresenter mPresenter;
    private ProgressBar mProgressBar;
    private List<MovieListUIModel>[] mUiModelsArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MovieListPresenter(App.getRepo(), this);
        mPresenter.attachView(this);
        mPopularMovieRecyclerView = mBinding.recyclerViewPopularMovies;
        mTopMovieRecyclerView = mBinding.recyclerViewTopMovies;
        mUpcomingMovieRecyclerView = mBinding.recyclerViewUpcomingMovies;
        mProgressBar = mBinding.progressCircular;
        mUiModelsArray = new ArrayList[3];
        mUiModelsArray[0] = new ArrayList<>();
        mUiModelsArray[1] = new ArrayList<>();
        mUiModelsArray[2] = new ArrayList<>();
        mBinding.textViewPopularMovies.setText("Popular Movies");
        mBinding.textViewTopMovies.setText("Top Movies");
        mBinding.textViewUpcomingMovies.setText("Upcoming Movies");

        mPresenter.fetchData();
    }

    @Override
    public void submitList(List<MovieListUIModel> uiModel) {
        if (mUiModelsArray[0].isEmpty()) {
            mUiModelsArray[0] = uiModel;
        }
        else if (mUiModelsArray[1].isEmpty()) {
            mUiModelsArray[1] = uiModel;
        }
        else {
            mUiModelsArray[2] = uiModel;
        }
    }

    @Override
    public void showData() {
        MovieListAdapter popularMoviesAdapter = new MovieListAdapter(mUiModelsArray[0]);
        MovieListAdapter topRatedMoviesAdapter = new MovieListAdapter(mUiModelsArray[1]);
        MovieListAdapter upcomingMoviesAdapter = new MovieListAdapter(mUiModelsArray[2]);

        mPopularMovieRecyclerView.setAdapter(popularMoviesAdapter);
        mTopMovieRecyclerView.setAdapter(topRatedMoviesAdapter);
        mUpcomingMovieRecyclerView.setAdapter(upcomingMoviesAdapter);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}