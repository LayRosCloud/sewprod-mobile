package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PackageAdapter;
import com.betrayal.atcutter.models.Package;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageGetAllCallback extends CallbackWrapper<List<Package>> {
    private final ListView listView;
    public PackageGetAllCallback(@NonNull Context context, @NonNull ListView listView){
        super(context);
        this.listView = listView;
    }

    @Override
    protected void successResponse(Response<List<Package>> item) {
        ArrayAdapter<Package> adapter = new PackageAdapter(context, item.body());
        listView.setAdapter(adapter);
    }

    @Override
    protected String getErrorMessage() {
        return "Проверьте интернет соединение!";
    }
}
