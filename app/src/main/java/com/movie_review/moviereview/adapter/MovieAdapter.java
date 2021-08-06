package com.movie_review.moviereview.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movie_review.moviereview.R;
import com.movie_review.moviereview.activity.DetailActivity;
import com.movie_review.moviereview.model.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context context;
    private List<Result> resultList;

    public MovieAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_movie, parent, false);

        MovieAdapter.MyViewHolder viewHolder = new MovieAdapter.MyViewHolder(view);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), DetailActivity.class);
                Result result = new Result();
                result.setOriginalTitle(resultList.get(viewHolder.getAdapterPosition()).getOriginalTitle());
                result.setOverview(resultList.get(viewHolder.getAdapterPosition()).getOverview());
                result.setPosterPath(resultList.get(viewHolder.getAdapterPosition()).getPosterPath());
                result.setReleaseDate(resultList.get(viewHolder.getAdapterPosition()).getReleaseDate());
                result.setVoteAverage(resultList.get(viewHolder.getAdapterPosition()).getVoteAverage());
                result.setVoteCount(resultList.get(viewHolder.getAdapterPosition()).getVoteCount());
                intent.putExtra(DetailActivity.EXTRA_MOVIE, result);
                parent.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(resultList.get(position).getTitle());
        holder.tvDesc.setText(resultList.get(position).getOverview());
        holder.tvDate.setText(resultList.get(position).getReleaseDate());
        Glide.with(context).load("https://image.tmdb.org/t/p/w185" + resultList.get(position).getPosterPath()).into(holder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvDesc, tvDate;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDate = itemView.findViewById(R.id.tvDate);

            relativeLayout = itemView.findViewById(R.id.layoutMovie);
        }
    }
}

