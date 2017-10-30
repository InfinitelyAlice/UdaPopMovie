package com.infinitelyalice.udapopmovie.data;

import com.infinitelyalice.udapopmovie.model.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ME on 2017/10/24.
 */

public interface InternetRequest_Interface {
    @GET("{sort}")
    Call<MoviesData> getInternetData(@Path("sort")String sort, @Query("api_key") String api_key);

}
