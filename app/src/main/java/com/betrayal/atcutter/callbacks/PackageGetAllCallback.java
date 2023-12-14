package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PackageAdapter;
import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.models.Package;
import com.betrayal.atcutter.models.Party;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageGetAllCallback implements Callback<List<Package>> {
    private final ListView listView;
    private final Context context;
    public PackageGetAllCallback(@NonNull Context context, @NonNull ListView listView){
        this.listView = listView;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
        if(response.isSuccessful()){
            ArrayAdapter<Package> adapter = new PackageAdapter(context, response.body());
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<List<Package>> call, Throwable t) {

    }
}
