package com.sholasstore.themovieapp.movie_list_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholasstore.themovieapp.App;
import com.sholasstore.themovieapp.R;
import com.sholasstore.themovieapp.databinding.FragmentMovieListBinding;
import com.sholasstore.themovieapp.main_activity.IMainActivity;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieListFragment extends Fragment implements MovieListContract.View {
    private FragmentMovieListBinding mBinding;
    private List<MovieListUIModel> mPopularMovies;
    private List<MovieListUIModel> mTopMovies;
    private List<MovieListUIModel> mUpcomingMovies;
    @Inject MovieListContract.Presenter mPresenter;
    private String clazz = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App app = (App) getActivity().getApplication();
        app.getAppComponent().plusMovieList().inject(this);

        mPresenter.attachView(this);
        mPopularMovies = new ArrayList<>();
        mTopMovies = new ArrayList<>();
        mUpcomingMovies = new ArrayList<>();

        mBinding.textViewPopularMovies.setText(R.string.popular_movies);
        mBinding.textViewTopMovies.setText(R.string.top_movies);
        mBinding.textViewUpcomingMovies.setText(R.string.upcoming_movies);

        mPresenter.fetchData();
    }

    @Override
    public void submitList(List<MovieListUIModel> uiModels) {
        Log.d(clazz, "LOG - Submit List called on the view from the presenter");
        if (uiModels.isEmpty()) {
            Log.d(clazz, "LOG - List gotten from presenter empty, fetching from network");
            mPresenter.refresh();
            Log.d(clazz, "LOG - Presenter fetched from network");
        }
        else if (uiModels.get(1).getFlag() == MovieListDbFlag.POPULAR_MOVIES) {
            mPopularMovies = uiModels;
        }
        else if (uiModels.get(1).getFlag() == MovieListDbFlag.TOP_MOVIES) {
            mTopMovies = uiModels;
        }
        else {
            mUpcomingMovies = uiModels;
        }
    }

    @Override
    public void showData() {
        MovieListAdapter popularMoviesAdapter = new MovieListAdapter(mPopularMovies, getActivity(), (IMainActivity) getActivity());
        MovieListAdapter topRatedMoviesAdapter = new MovieListAdapter(mTopMovies, getActivity(), (IMainActivity) getActivity());
        MovieListAdapter upcomingMoviesAdapter = new MovieListAdapter(mUpcomingMovies, getActivity(), (IMainActivity) getActivity());

        mBinding.recyclerViewPopularMovies.setAdapter(popularMoviesAdapter);
        mBinding.recyclerViewTopMovies.setAdapter(topRatedMoviesAdapter);
        mBinding.recyclerViewUpcomingMovies.setAdapter(upcomingMoviesAdapter);
    }

    @Override
    public void showLoading() {
        mBinding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mBinding.progressCircular.setVisibility(View.GONE);
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