package com.betrayal.atcutter.callbacks;


import android.content.Context;
import android.content.Intent;

import com.betrayal.atcutter.models.Auth;
import com.betrayal.atcutter.models.Person;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.views.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginCallback implements Callback<Auth> {
    private final Context context;
    public LoginCallback(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(Call<Auth> call, Response<Auth> response) {
        if(response.isSuccessful()){
            ServerConstants.User = response.body();
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void onFailure(Call<Auth> call, Throwable t) {

    }
}
