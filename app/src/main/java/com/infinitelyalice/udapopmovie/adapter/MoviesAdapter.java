package com.infinitelyalice.udapopmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.infinitelyalice.udapopmovie.R;
import com.infinitelyalice.udapopmovie.data.StringForm;
import com.infinitelyalice.udapopmovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ME on 2017/10/25.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> movies;
    private MovieItemListener itemListener;
    private Context context;
    public MoviesAdapter(Context context, List<Movie> movies, MovieItemListener itemListener) {
        this.context = context;
        this.movies = movies;
        this.itemListener = itemListener;
    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(v, itemListener);
    }
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Picasso.with(context)
                .load(StringForm.IMA_SRC + movie.getPosterPath())
                .placeholder(R.mipmap.image_one)
                .error(R.mipmap.image_one)
                .into(holder.movieViewImg);
    }
    @Override public int getItemCount() {
        return movies.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_item_img) ImageView movieViewImg;
        MovieItemListener itemListener;

        public ViewHolder(View itemView, MovieItemListener itemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }
        @Override public void onClick(View view) {
            Movie movie = getItem(getAdapterPosition());
            this.itemListener.onMovieClick(movie);
        }
    }
    private Movie getItem(int adapterPosition) {
        return movies.get(adapterPosition);
    }
    public void updateMovieData(List<Movie> movie) {
        movies = movie;
        notifyDataSetChanged();
    }
    public interface MovieItemListener {
        void onMovieClick(Movie movie);
    }
}