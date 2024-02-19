package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.models.PartyEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Response;

public class PartySpinnerCallback extends CallbackWrapper<List<PartyEntity>>{
    private final Spinner spinner;
    public PartySpinnerCallback(@NonNull Context context, Spinner spinner) {
        super(context);
        this.spinner = spinner;
    }

    @Override
    protected void successResponse(Response<List<PartyEntity>> item) {
        List<PartyEntity> parties = item.body();
        Date now = new Date();

        parties = parties.stream()
                .filter(x -> x.getDateStart().getYear() >= now.getYear() && x.getDateStart().getYear() <= (now.getYear() + 1))
                .collect(Collectors.toList());

        ArrayAdapter<PartyEntity> adapter = new PartyAdapter(context, parties);

        spinner.setAdapter(adapter);
    }

    @Override
    protected String getErrorMessage() {
        return null;
    }
}
