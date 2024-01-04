package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.Material;
import com.betrayal.atcutter.models.Party;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface MaterialRepository extends Repository {
    @GET("/v1/materials/")
    Call<List<Material>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/materials/{id}")
    Call<Material> get(@Path("id") int id, @Header("Authorization") String authorization);
}
