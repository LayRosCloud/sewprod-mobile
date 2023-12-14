package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.MaterialAdapter;
import com.betrayal.atcutter.adapters.SizeAdapter;
import com.betrayal.atcutter.models.Material;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialGetAllCallback implements Callback<List<Material>> {
    private final Spinner spinner;
    private final Context context;

    public MaterialGetAllCallback(@NonNull Context context, @NonNull Spinner spinner){
        this.spinner = spinner;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Material>> call, Response<List<Material>> response) {
        if(response.isSuccessful()){
            MaterialAdapter materialAdapter = new MaterialAdapter(context, response.body());
            spinner.setAdapter(materialAdapter);
        }
    }

    @Override
    public void onFailure(Call<List<Material>> call, Throwable t) {

    }
}
