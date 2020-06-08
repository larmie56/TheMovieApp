package com.sholasstore.themovieapp.movie_details_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.sholasstore.themovieapp.App;
import com.sholasstore.themovieapp.StringUtil;
import com.sholasstore.themovieapp.databinding.FragmentMovieDetailsBinding;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import javax.inject.Inject;

public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {
    public static String MOVIE_ID_EXTRA = "movie_id_extra";

    private FragmentMovieDetailsBinding mBinding;
    private int movieId;
    @Inject MovieDetailsContract.Presenter mPresenter;
    @Inject
    RemoteRepoImpl mRepo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            movieId = bundle.getInt(MOVIE_ID_EXTRA);
        }

        App app = (App) getActivity().getApplication();
        app.getAppComponent().plusMovieDetails().inject(this);
        mPresenter.attachView(this);
        mPresenter.fetchData(movieId);
    }

    @Override
    public void showMovieDetails(MovieDetailsUIModel uiModel) {
        uiModel.setMovieId(movieId);
        Glide.with(mBinding.getRoot().getContext())
                .load(StringUtil.appendBaseImageUrl(uiModel.getPosterPath()))
                .into(mBinding.imageViewMoviePoster);

        mBinding.textViewMovieTitle.setText(uiModel.getMovieTitle());
        mBinding.textViewMovieOverview.setText(uiModel.getMovieOverview());
        mBinding.textViewMovieGenre.setText(StringUtil.formatMovieGenre(uiModel.getMovieGenres()));
        mBinding.textViewReleaseDate.setText(StringUtil.formatReleaseDateString(uiModel.getReleaseDate()));
        mBinding.textViewRevenue.setText(StringUtil.formatRevenueString(uiModel.getRevenue()));
        mBinding.textViewRuntime.setText(StringUtil.formatMovieRuntimeInt(uiModel.getRuntime()));
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
}