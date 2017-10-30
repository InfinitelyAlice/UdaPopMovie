package com.infinitelyalice.udapopmovie.dao;

import com.infinitelyalice.udapopmovie.data.InternetRequest_Interface;
import com.infinitelyalice.udapopmovie.data.StringForm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ME on 2017/10/24.
 */

public class GetIRI_Instance {
    public static Retrofit getRetrofit(String base_url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static InternetRequest_Interface creatIRI_Instance(){        //TODO 创建网络请求接口实例
        return getRetrofit(StringForm.BASE_URL).create(InternetRequest_Interface.class);
    }
}
