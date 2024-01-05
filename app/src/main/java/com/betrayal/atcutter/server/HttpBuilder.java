package com.betrayal.atcutter.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class HttpBuilder {
    private final Retrofit retrofit;

    public HttpBuilder(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public <TRepository> TRepository createService(Class<TRepository> owner){
        return retrofit.create(owner);
    }

    public String getAuthorizationHeader(){
        return "Bearer " + ServerConstants.User.getToken();
    }
}
