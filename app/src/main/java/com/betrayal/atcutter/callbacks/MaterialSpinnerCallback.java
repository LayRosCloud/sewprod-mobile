package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.MaterialAdapter;
import com.betrayal.atcutter.models.MaterialEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;

import java.util.List;

import retrofit2.Response;

public class MaterialSpinnerCallback extends CallbackWrapper<List<MaterialEntity>> {
    private final Spinner spinner;
    public MaterialSpinnerCallback(@NonNull Context context, Spinner spinner) {
        super(context);
        this.spinner = spinner;
    }

    @Override
    protected void successResponse(Response<List<MaterialEntity>> item) {
        ArrayAdapter<MaterialEntity> adapter = new MaterialAdapter(context, item.body());
        spinner.setAdapter(adapter);
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
