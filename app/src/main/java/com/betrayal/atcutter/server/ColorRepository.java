package com.betrayal.atcutter.server;

import com.betrayal.atcutter.models.Color;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ColorRepository {
    @GET("/v1/colors/")
    Call<List<Color>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/colors/{id}")
    Call<Color> get(@Path("id") int id, @Header("Authorization") String authorization);
}
