package com.infinitelyalice.udapopmovie.MovieDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.infinitelyalice.udapopmovie.R;
import com.infinitelyalice.udapopmovie.data.StringForm;
import com.infinitelyalice.udapopmovie.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;
    @BindView(R.id.movie_name) TextView movieName;
    @BindView(R.id.movie_poster) ImageView moviePoster;
    @BindView(R.id.movie_overview) TextView movieOverview;
    @BindView(R.id.movie_rating) TextView movieRating;
    @BindView(R.id.movie_release_data) TextView movieReleaseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        movie = getIntent().getParcelableExtra("to_movie_detail_activity");
        Picasso.with(this)
                .load(StringForm.IMA_SRC + movie.getPosterPath())
                .into(moviePoster);
        movieName.setText(movie.getTitle());
        movieOverview.setText(movie.getOverview());
        String rating = movie.getVoteAverage() + StringForm.VOTE_AVERAGE_CONCAT;
        movieRating.setText(rating);
        movieReleaseData.setText(movie.getReleaseData());
    }
}
