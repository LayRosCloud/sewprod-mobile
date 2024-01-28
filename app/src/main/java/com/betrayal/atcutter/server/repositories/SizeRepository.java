package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.SizeEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SizeRepository {
    @GET("/v1/sizes/")
    Call<List<SizeEntity>> getAll(@Header("Authorization") String authorization);
}
