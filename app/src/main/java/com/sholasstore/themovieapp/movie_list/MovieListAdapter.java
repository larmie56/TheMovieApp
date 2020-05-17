package com.sholasstore.themovieapp.movie_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sholasstore.themovieapp.R;
import com.sholasstore.themovieapp.StringUtil;
import com.sholasstore.themovieapp.databinding.MovieListItemBinding;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private final List<MovieListUIModel> mUIModels;

    public MovieListAdapter(List<MovieListUIModel> uiModels) {
        mUIModels = uiModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final MovieListItemBinding binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieListUIModel uiModel = mUIModels.get(position);
        holder.bind(uiModel);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.movie_list_item;
    }

    @Override
    public int getItemCount() {
        return mUIModels.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        MovieListItemBinding mBinding;

        ViewHolder(MovieListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(MovieListUIModel uiModel) {
            Glide.with(mBinding.getRoot().getContext())
                    .load(StringUtil.appendBaseImageUrl(uiModel.getPosterPath()))
                    .into(mBinding.imageViewMoviePoster);
        }
    }
}
