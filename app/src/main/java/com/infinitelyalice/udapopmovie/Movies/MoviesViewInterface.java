package com.infinitelyalice.udapopmovie.Movies;

import com.infinitelyalice.udapopmovie.model.Movie;

import java.util.ArrayList;

/**
 * Created by ME on 2017/10/26.
 */

public interface MoviesViewInterface {
    void showMovies(ArrayList<Movie> movie);
    void showErrorMessage();
}
