package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.models.Party;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyGetAllCallback implements Callback<List<Party>> {
    private final GridView gridView;
    private final Context context;
    public PartyGetAllCallback(@NonNull Context context,@NonNull GridView gridView){
        this.gridView = gridView;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Party>> call, Response<List<Party>> response) {
        if(response.isSuccessful()){
            ArrayAdapter<Party> adapter = new PartyAdapter(context, response.body());
            gridView.setAdapter(adapter);
        }
        else {
            try {
                Log.d("ERROR_REQUEST", response.errorBody().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onFailure(Call<List<Party>> call, Throwable t) {
        Log.d("ERROR_REQUEST", t.getMessage());
    }
}
