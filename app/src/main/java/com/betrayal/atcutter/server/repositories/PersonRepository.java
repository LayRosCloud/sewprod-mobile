package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.SecuritySuccessfulEntity;
import com.betrayal.atcutter.models.PersonEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonRepository {
    @GET("/v1/persons/{id}")
    Call<PersonEntity> get(@Path("id") int id, @Header("Authorization") String authorization);

    @POST("/v1/auth/")
    Call<SecuritySuccessfulEntity> login(@Body() PersonEntity person);
}
