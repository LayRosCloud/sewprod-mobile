package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.MaterialEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface MaterialRepository {
    @GET("/v1/materials/")
    Call<List<MaterialEntity>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/materials/{id}")
    Call<MaterialEntity> get(@Path("id") int id, @Header("Authorization") String authorization);
}
