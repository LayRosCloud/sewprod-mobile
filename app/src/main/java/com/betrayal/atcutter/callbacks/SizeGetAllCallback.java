package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.SizeAdapter;
import com.betrayal.atcutter.models.Party;
import com.betrayal.atcutter.models.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SizeGetAllCallback implements Callback<List<Size>> {

    private final Spinner spinner;
    private final Context context;
    public SizeGetAllCallback(@NonNull Context context, @NonNull Spinner spinner){
        this.spinner = spinner;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Size>> call, Response<List<Size>> response) {
        if(response.isSuccessful()){
            SizeAdapter sizeAdapter = new SizeAdapter(context, response.body());
            spinner.setAdapter(sizeAdapter);
        }
    }

    @Override
    public void onFailure(Call<List<Size>> call, Throwable t) {

    }
}
