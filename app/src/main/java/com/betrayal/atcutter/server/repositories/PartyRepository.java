package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.PartyEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PartyRepository {
    @GET("/v1/parties/")
    Call<List<PartyEntity>> getAll(@Header("Authorization") String authorization);

    @POST("/v1/parties/")
    Call<PartyEntity> create(@Body() PartyEntity party, @Header("Authorization") String authorization);
}
