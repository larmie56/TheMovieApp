package com.sholasstore.themovieapp.movie_list_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sholasstore.themovieapp.main_activity.IMainActivity;
import com.sholasstore.themovieapp.StringUtil;
import com.sholasstore.themovieapp.databinding.MovieListItemBinding;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder> {
    private List<MovieListUIModel> mUIModels;
    private Context mContext;
    private IMainActivity mIMainActivity;

    public MovieListAdapter(List<MovieListUIModel> uiModels, Context context, IMainActivity iMainActivity) {
        mUIModels = uiModels;
        mContext = context;
        mIMainActivity = iMainActivity;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final MovieListItemBinding binding =
                    MovieListItemBinding.inflate(LayoutInflater.from(mContext), parent,false);
            return new MovieItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieItemViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mUIModels.size();
    }

    public void setUIModels(List<MovieListUIModel> uiModels) {
        mUIModels = uiModels;
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder {
        MovieListItemBinding mItemBinding;
        //IMainActivity mIMainActivity;

        MovieItemViewHolder(MovieListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
        }

        void bind(int position) {
            final View bindingRoot = mItemBinding.getRoot();

            final MovieListUIModel uiModel = mUIModels.get(position);

            Glide.with(bindingRoot.getContext())
                    .load(StringUtil.appendBaseImageUrl(uiModel.getPosterPath()))
                    .into(mItemBinding.imageViewMoviePoster);

            bindingRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIMainActivity.recyclerRowItemClicked(uiModel.getMovieId());
                }
            });
        }
    }
}
