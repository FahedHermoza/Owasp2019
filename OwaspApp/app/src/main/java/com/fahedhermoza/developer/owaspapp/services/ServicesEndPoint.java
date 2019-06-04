package com.fahedhermoza.developer.owaspapp.services;

import com.fahedhermoza.developer.owaspapp.models.ResponseLogin;
import com.fahedhermoza.developer.owaspapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServicesEndPoint {
    @Headers("Content-Type: application/json")
    @POST("login/")
    Call<ResponseLogin> findUser(@Body User User);
}
