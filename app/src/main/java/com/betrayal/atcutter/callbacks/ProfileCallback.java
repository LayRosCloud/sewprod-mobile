package com.betrayal.atcutter.callbacks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.models.PersonEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.scripts.ProfileHandler;

import retrofit2.Response;

public class ProfileCallback extends CallbackWrapper<PersonEntity>{
    private final ProfileHandler handler;
    public ProfileCallback(@NonNull Context context, ProfileHandler handler) {
        super(context);
        this.handler = handler;
        showLoadingDialog();
    }

    @Override
    protected void successResponse(Response<PersonEntity> item) {
        PersonEntity entity = item.body();

        handler.setEmail(entity.getEmail());
        handler.setDate(entity.getDateRegistration());
        handler.setUid(entity.getUid());
        handler.setFullName(entity.getLastName(), entity.getFirstName());
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
