package com.betrayal.atcutter.server;

import com.betrayal.atcutter.models.Party;
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

public interface PartyRepository {
    @GET("/v1/parties/")
    Call<List<Party>> getAll(@Header("Authorization") String authorization);

    @GET("/v1/parties/{id}")
    Call<Party> get(@Path("id") int id, @Header("Authorization") String authorization);

    @POST("/v1/parties/")
    Call<Party> create(@Body() Party party, @Header("Authorization") String authorization);
}
