package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.PriceEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PriceRepository {
    @POST("/v1/prices")
    Call<PriceEntity> create(@Body() PriceEntity price, @Header("Authorization") String authorization);
}
