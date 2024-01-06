package com.betrayal.atcutter.callbacks;

import android.content.Context;

import com.betrayal.atcutter.models.PartyEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;

import java.util.List;

import retrofit2.Response;

public class PartyGetAllCallback extends CallbackWrapper<List<PartyEntity>>{
    private InsideCallback<List<PartyEntity>> callback;

    public PartyGetAllCallback(Context context) {
        super(context);
        showLoadingDialog();
    }

    public void subscribe(InsideCallback<List<PartyEntity>> callback){
        this.callback = callback;
    }
    @Override
    protected void successResponse(Response<List<PartyEntity>> item) {
        final List<PartyEntity> partyList = item.body();
        if(callback != null){
            callback.success(partyList);
        }
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
