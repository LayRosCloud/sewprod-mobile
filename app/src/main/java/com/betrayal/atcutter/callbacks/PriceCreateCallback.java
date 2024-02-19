package com.betrayal.atcutter.callbacks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.models.ClothOperationEntity;
import com.betrayal.atcutter.models.PriceEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.ClothOperationRepository;

import retrofit2.Call;
import retrofit2.Response;

public class PriceCreateCallback extends CallbackWrapper<PriceEntity> {

    private final ClothOperationEntity clothOperation;

    public PriceCreateCallback(@NonNull Context context, ClothOperationEntity entity) {
        super(context);
        clothOperation = entity;
    }

    @Override
    protected void successResponse(Response<PriceEntity> item) {
        PriceEntity price = item.body();
        HttpBuilder httpBuilder = new HttpBuilder();

        clothOperation.setPriceId(price.getId());
        ClothOperationRepository rep = httpBuilder.createService(ClothOperationRepository.class);
        Call<ClothOperationEntity> clothOperationCall = rep.create(clothOperation, httpBuilder.getAuthorizationHeader());
        ClothOperationCreateCallback clothOperationCreateCallback = new ClothOperationCreateCallback(context);
        clothOperationCall.enqueue(clothOperationCreateCallback);
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
