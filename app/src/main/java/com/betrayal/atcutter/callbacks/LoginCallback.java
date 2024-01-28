package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.SecuritySuccessfulEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.views.AuthFragment;
import com.betrayal.atcutter.views.HubActivity;

import retrofit2.Response;

public class LoginCallback extends CallbackWrapper<SecuritySuccessfulEntity> {
    private InsideCallback<SecuritySuccessfulEntity> callback;

    public LoginCallback(Context context){
        super(context);
        showLoadingDialog();
    }

    public void setCallback(InsideCallback<SecuritySuccessfulEntity> callback){
        this.callback = callback;
    }

    @Override
    protected void successResponse(Response<SecuritySuccessfulEntity> item) {
        ServerConstants.CurrentUser = item.body();
        if(callback != null){
            callback.success(item.body());
        }

    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_LOGIN;
    }
}
