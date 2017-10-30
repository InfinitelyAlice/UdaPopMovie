package com.infinitelyalice.udapopmovie.Movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.infinitelyalice.udapopmovie.MovieDetail.MovieDetailActivity;
import com.infinitelyalice.udapopmovie.R;
import com.infinitelyalice.udapopmovie.adapter.MoviesAdapter;
import com.infinitelyalice.udapopmovie.dao.GetIRI_Instance;
import com.infinitelyalice.udapopmovie.data.StringForm;
import com.infinitelyalice.udapopmovie.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesViewInterface{
    public static final String MOVIE_LIST = "movieList";
    public static final String CURRENT_SORT = "currentSort";
    MoviesPresenter moviesPresenter;
    @BindView(R.id.movie_rv) RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ArrayList<Movie> movie;
    int currentSort = 1;
    MenuItem popMenuItem;
    MenuItem ratingMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().setExitTransition(new Explode());
        moviesAdapter = new MoviesAdapter(this, new ArrayList<Movie>(0), movieItemListener);
        moviesPresenter = new MoviesPresenter(GetIRI_Instance.creatIRI_Instance(), this);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(MOVIE_LIST)){
                showMovies(savedInstanceState.<Movie>getParcelableArrayList(MOVIE_LIST));
                currentSort = savedInstanceState.getInt(CURRENT_SORT);
            }
        }else {
            moviesPresenter.initData(StringForm.SORTING_WAY_POPULAR);
            recyclerView.setAdapter(moviesAdapter);
            recyclerView.setHasFixedSize(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIE_LIST, movie);
        outState.putInt(CURRENT_SORT, currentSort);
    }

    private MoviesAdapter.MovieItemListener movieItemListener = new MoviesAdapter.MovieItemListener() {
        @Override
        public void onMovieClick(Movie movie) {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("to_movie_detail_activity", movie);
            startActivity(intent);
        }
    };



    @Override
    public void showMovies(ArrayList<Movie> movie) {
        moviesAdapter.updateMovieData(movie);
        this.movie = movie;
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, StringForm.REQUEST_INTERNET_ERROR,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        popMenuItem= menu.findItem(R.id.popular);
        ratingMenuItem = menu.findItem(R.id.rating);
        if (currentSort == 2){
            popMenuItem.setChecked(false);
            ratingMenuItem.setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.popular:
                if (currentSort == 1){//// TODO: 2017/10/27 1  表示已有popular数据。
                    break;
                }
                moviesPresenter.initData(StringForm.SORTING_WAY_POPULAR);
                popMenuItem.setChecked(true);
                ratingMenuItem.setChecked(false);
                currentSort = 1;
                break;
            case R.id.rating:
                if (currentSort == 2){
                    break;
                }
                moviesPresenter.initData(StringForm.SORTING_WAY_TOP_RATED);
                popMenuItem.setChecked(false);
                ratingMenuItem.setChecked(true);
                currentSort = 2;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
