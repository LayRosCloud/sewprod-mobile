package com.betrayal.atcutter.callbacks;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.Auth;
import com.betrayal.atcutter.models.Person;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.views.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginCallback implements Callback<Auth> {
    private final View context;
    public LoginCallback(View context){
        this.context = context;
    }

    @Override
    public void onResponse(@NonNull Call<Auth> call, Response<Auth> response) {
        if(response.isSuccessful()){
            ServerConstants.User = response.body();
            NavController controller = Navigation.findNavController(context);
            controller.navigate(R.id.action_authFragment_to_partyFragment);
        }
    }

    @Override
    public void onFailure(@NonNull Call<Auth> call, Throwable t) {

    }
}
