package com.betrayal.atcutter.callbacks;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.views.dialogues.LoadingDialog;
import com.betrayal.atcutter.views.dialogues.MessageDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CallbackWrapper<T> implements Callback<T> {

    protected Context context;
    private final Dialog loadingDialog;
    private boolean canDisableLoadingDialog = true;
    public CallbackWrapper(@NonNull Context context){
        this.context = context;
        loadingDialog = new LoadingDialog(context);

    }
    public void showLoadingDialog(){
        loadingDialog.show();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            successResponse(response);
            if(isCanDisableLoadingDialog()){
                loadingDialog.dismiss();
            }
        }else{
            try {
                Log.d("ERROR_NETWORK_LOADING", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            loadingDialog.dismiss();
            Dialog dialog = new MessageDialog(context, "Ошибка", getErrorMessage());
            dialog.show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        loadingDialog.dismiss();
        Dialog dialog = new MessageDialog(context, "Непредвиденная ошибка", t.getMessage());
        dialog.show();
    }

    protected abstract void successResponse(Response<T> item);
    protected abstract String getErrorMessage();

    public boolean isCanDisableLoadingDialog() {
        return canDisableLoadingDialog;
    }

    public void setCanDisableLoadingDialog(boolean canDisableLoadingDialog) {
        this.canDisableLoadingDialog = canDisableLoadingDialog;
    }

    public void dismissDialog(){
        loadingDialog.dismiss();
    }
}
