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
import com.betrayal.atcutter.views.HubActivity;

import retrofit2.Response;

public class LoginCallback extends CallbackWrapper<SecuritySuccessfulEntity> {
    private final View view;
    private final String email;
    private final String password;

    public LoginCallback(@NonNull View view, String email, String password){
        super(view.getContext());
        this.view = view;
        this.email = email;
        this.password = password;
        showLoadingDialog();
    }

    @Override
    protected void successResponse(Response<SecuritySuccessfulEntity> item) {
        ServerConstants.CurrentUser = item.body();
        Bundle args = new Bundle();
        args.putString("EMAIL", email);
        args.putString("EMAIL", password);
        NavController controller = Navigation.findNavController(view);
        controller.navigate(R.id.action_authFragment_to_pinCodeSaveFragment, args);
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_LOGIN;
    }
}
