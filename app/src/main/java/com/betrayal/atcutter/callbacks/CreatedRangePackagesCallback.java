package com.betrayal.atcutter.callbacks;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.ClothOperationEntity;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;

import java.util.List;

import retrofit2.Response;

public class CreatedRangePackagesCallback extends CallbackWrapper<List<PackageEntity>>{
    private final View view;
    private InsideCallback<List<PackageEntity>> callback;
    public CreatedRangePackagesCallback(@NonNull Context context, View view) {
        super(context);
        this.view = view;
        showLoadingDialog();
    }

    public void subscribe(InsideCallback<List<PackageEntity>> callback){
        this.callback = callback;
    }

    @Override
    protected void successResponse(Response<List<PackageEntity>> item) {
        if(callback != null){
            callback.success(item.body());
        }
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
