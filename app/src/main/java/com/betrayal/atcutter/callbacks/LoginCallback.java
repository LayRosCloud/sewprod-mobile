package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.content.Intent;

import com.betrayal.atcutter.models.Auth;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.views.HubActivity;

import retrofit2.Response;

public class LoginCallback extends CallbackWrapper<Auth> {
    public LoginCallback(Context context){
        super(context);
    }

    @Override
    protected void successResponse(Response<Auth> item) {
        ServerConstants.User = item.body();
        Intent intent = new Intent(context, HubActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected String getErrorMessage() {
        return "Проверьте правильность написания почты или пароля!";
    }
}
