package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.ClothOperationEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ClothOperationRepository {
    @POST("/v1/clothoperations")
    Call<ClothOperationEntity> create(@Body() ClothOperationEntity clothOperation, @Header("Authorization") String authorization);
}
