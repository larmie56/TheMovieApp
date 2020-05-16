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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.sholasstore.themovieapp.App;
import com.sholasstore.themovieapp.databinding.FragmentMovieListBinding;

import java.util.List;

public class MovieListFragment extends Fragment implements MovieListContract.View {
    private RecyclerView mRecyclerView;
    private FragmentMovieListBinding mBinding;
    private MovieListPresenter mPresenter;
    private ProgressBar mProgressBar;

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
        mRecyclerView = mBinding.recyclerViewMovieList;
        mProgressBar = mBinding.progressCircular;

        mPresenter.fetchData();
    }

    @Override
    public void showData(List<MovieListUIModel> uiModels) {
        //mRecyclerView.setAdapter(new MovieListAdapter(uiModels));
        MovieListAdapter adapter = new MovieListAdapter(uiModels);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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