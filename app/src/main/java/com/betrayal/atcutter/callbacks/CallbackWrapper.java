package com.betrayal.atcutter.callbacks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.TextView;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.views.dialogues.MessageDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CallbackWrapper<T> implements Callback<T> {

    protected Context context;
    public CallbackWrapper(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            successResponse(response);
        }else{
            Dialog dialog = new MessageDialog(context, "Ошибка", getErrorMessage());
            dialog.show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Dialog dialog = new MessageDialog(context, "Непредвиденная ошибка", t.getMessage());
        dialog.show();
    }

    protected abstract void successResponse(Response<T> item);
    protected abstract String getErrorMessage();
}
