package com.betrayal.atcutter.server;

import com.betrayal.atcutter.models.Package;
import com.betrayal.atcutter.models.SuccessDelete;

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
    Call<List<Package>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/packages/")
    Call<List<Package>> getAll(@Query("partyId") int partyId, @Header("Authorization") String authorization);

    @GET("/v1/packages/{id}")
    Call<Package> get(@Path("id") int id, @Header("Authorization") String authorization);

    @POST("/v1/packages/")
    Call<Package> create(@Body() Package packageEntity, @Header("Authorization") String authorization);

    @PUT("/v1/packages/{id}")
    Call<Package> update(@Path("id") int id, @Body() Package party, @Header("Authorization") String authorization);

    @DELETE("/v1/packages/{id}")
    Call<SuccessDelete> delete(@Path("id") int id, @Header("Authorization") String authorization);
}
