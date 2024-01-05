package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.content.Intent;

import com.betrayal.atcutter.models.SecuritySuccessfulEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.views.HubActivity;

import retrofit2.Response;

public class LoginCallback extends CallbackWrapper<SecuritySuccessfulEntity> {
    public LoginCallback(Context context){
        super(context);
        showLoadingDialog();
    }

    @Override
    protected void successResponse(Response<SecuritySuccessfulEntity> item) {
        ServerConstants.User = item.body();
        Intent intent = new Intent(context, HubActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_LOGIN;
    }
}
