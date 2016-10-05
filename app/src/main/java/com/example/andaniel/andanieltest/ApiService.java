package com.example.andaniel.andanieltest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public interface ApiService
{
    @POST("user")
    Call<ResponseBody> user(@Header("PHP_AUTH_USER") String email, @Header("PHP_AUTH_PW") String password);
}