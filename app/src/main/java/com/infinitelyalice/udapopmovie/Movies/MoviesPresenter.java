package com.infinitelyalice.udapopmovie.Movies;

import com.infinitelyalice.udapopmovie.data.InternetRequest_Interface;
import com.infinitelyalice.udapopmovie.data.StringForm;
import com.infinitelyalice.udapopmovie.model.Movie;
import com.infinitelyalice.udapopmovie.model.MoviesData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ME on 2017/10/26.
 */

public class MoviesPresenter {
    private  final InternetRequest_Interface internetRequest;
    private  final MoviesViewInterface moviesView;

    public MoviesPresenter(InternetRequest_Interface internetRequest, MoviesViewInterface moviesView){
        this.internetRequest = internetRequest;
        this.moviesView = moviesView;
    }

    public void initData(String sort){
        internetRequest.getInternetData(sort, StringForm.API_KEY).enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {
                ArrayList<Movie> results = response.body().getResults();
                moviesView.showMovies(results);
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {
                moviesView.showErrorMessage();
            }
        });
    }

}
