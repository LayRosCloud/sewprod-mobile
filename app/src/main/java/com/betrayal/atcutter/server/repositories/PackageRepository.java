package com.betrayal.atcutter.server.repositories;

import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.models.SuccessDeletedOperation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PackageRepository {
    @GET("/v1/packages/")
    Call<List<PackageEntity>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/packages/")
    Call<List<PackageEntity>> getAll(@Query("partyId") int partyId, @Header("Authorization") String authorization);

    @GET("/v1/packages/{id}")
    Call<PackageEntity> get(@Path("id") int id, @Header("Authorization") String authorization);

    @POST("/v1/packages/")
    Call<PackageEntity> create(@Body() PackageEntity packageEntity, @Header("Authorization") String authorization);

    @PUT("/v1/packages/{id}")
    Call<PackageEntity> update(@Path("id") int id, @Body() PackageEntity party, @Header("Authorization") String authorization);

    @DELETE("/v1/packages/{id}")
    Call<SuccessDeletedOperation> delete(@Path("id") int id, @Header("Authorization") String authorization);
}
