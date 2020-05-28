package com.sholasstore.themovieapp.movie_list_fragment;

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
    private final List<MovieListUIModel> mUIModels;

    public MovieListAdapter(List<MovieListUIModel> uiModels) {
        mUIModels = uiModels;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final MovieListItemBinding binding =
                    MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
            return new MovieItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieItemViewHolder holder, int position) {
            final MovieListUIModel uiModel = mUIModels.get(position);
            holder.bind(uiModel);

        final View bindingRoot = holder.mItemBinding.getRoot();
        bindingRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IMainActivity iMainActivity = ((IMainActivity)bindingRoot.getContext());
                    iMainActivity.recyclerRowItemClicked(uiModel.getMovieId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return mUIModels.size();
    }

    static class MovieItemViewHolder extends RecyclerView.ViewHolder {
        MovieListItemBinding mItemBinding;

        MovieItemViewHolder(MovieListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
        }

        void bind(MovieListUIModel uiModel) {
            Glide.with(mItemBinding.getRoot().getContext())
                    .load(StringUtil.appendBaseImageUrl(uiModel.getPosterPath()))
                    .into(mItemBinding.imageViewMoviePoster);
        }
    }
}
