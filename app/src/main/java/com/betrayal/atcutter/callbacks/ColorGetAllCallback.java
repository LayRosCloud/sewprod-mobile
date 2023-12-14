package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.ColorAdapter;
import com.betrayal.atcutter.models.Color;
import com.betrayal.atcutter.models.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColorGetAllCallback implements Callback<List<Color>> {

    private final Spinner spinner;
    private final Context context;
    public ColorGetAllCallback(@NonNull Context context, @NonNull Spinner spinner){
        this.spinner = spinner;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Color>> call, Response<List<Color>> response) {
        if(response.isSuccessful()){
            ColorAdapter colorAdapter = new ColorAdapter(context, response.body());
            spinner.setAdapter(colorAdapter);
        }
    }

    @Override
    public void onFailure(Call<List<Color>> call, Throwable t) {

    }
}
