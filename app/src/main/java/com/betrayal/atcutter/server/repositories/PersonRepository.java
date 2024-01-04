package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.Auth;
import com.betrayal.atcutter.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonRepository extends Repository{
    @GET("/v1/persons")
    Call<List<Person>> getAll();

    @GET("/v1/persons/{id}")
    Call<Person> get(@Path("id") int id);

    @POST("/v1/persons/")
    Call<Person> register(@Body() Person person);

    @POST("/v1/auth/")
    Call<Auth> login(@Body() Person person);
}
