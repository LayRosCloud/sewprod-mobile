package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.models.PartyEntity;

import java.util.List;

import retrofit2.Response;

public class PartySpinnerCallback extends CallbackWrapper<List<PartyEntity>>{
    private final Spinner spinner;
    public PartySpinnerCallback(@NonNull Context context, Spinner spinner) {
        super(context);
        this.spinner = spinner;
    }

    @Override
    protected void successResponse(Response<List<PartyEntity>> item) {
        ArrayAdapter<PartyEntity> adapter = new PartyAdapter(context, item.body());
        spinner.setAdapter(adapter);
    }

    @Override
    protected String getErrorMessage() {
        return null;
    }
}
