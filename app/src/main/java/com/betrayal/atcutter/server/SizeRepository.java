package com.betrayal.atcutter.server;

import com.betrayal.atcutter.models.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SizeRepository {
    @GET("/v1/sizes/")
    Call<List<Size>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/sizes/{id}")
    Call<Size> get(@Path("id") int id, @Header("Authorization") String authorization);
}
