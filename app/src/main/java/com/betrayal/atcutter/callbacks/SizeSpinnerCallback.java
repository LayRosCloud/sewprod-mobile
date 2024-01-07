package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.SizeAdapter;
import com.betrayal.atcutter.models.SizeEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;

import java.util.List;

import retrofit2.Response;

public class SizeSpinnerCallback extends CallbackWrapper<List<SizeEntity>>{
    private final Spinner spinner;
    public SizeSpinnerCallback(@NonNull Context context, Spinner spinner) {
        super(context);
        this.spinner = spinner;
    }

    @Override
    protected void successResponse(Response<List<SizeEntity>> item) {
        ArrayAdapter<SizeEntity> adapter = new SizeAdapter(context, item.body());
        spinner.setAdapter(adapter);
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }

}
