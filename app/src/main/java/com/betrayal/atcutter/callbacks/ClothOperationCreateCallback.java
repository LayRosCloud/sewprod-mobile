package com.betrayal.atcutter.callbacks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.models.ClothOperationEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;

import retrofit2.Response;

public class ClothOperationCreateCallback extends CallbackWrapper<ClothOperationEntity> {
    private InsideCallback<ClothOperationEntity> callback;

    public ClothOperationCreateCallback(@NonNull Context context) {
        super(context);
    }

    public void subscribe(InsideCallback<ClothOperationEntity> callback){
        this.callback = callback;
    }

    @Override
    protected void successResponse(Response<ClothOperationEntity> item) {
        if(callback != null){
            callback.success(item.body());
        }
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
