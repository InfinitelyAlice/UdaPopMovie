package com.infinitelyalice.udapopmovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 2017/10/24.
 */

public class MoviesData {
    @Expose @SerializedName("page") private Integer page;
    @Expose @SerializedName("totalResults") private Integer total_results;
    @Expose @SerializedName("totalPages") private Integer total_pages;
    @Expose @SerializedName("results") private List<Movie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResults() {
        return (ArrayList<Movie>) results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
