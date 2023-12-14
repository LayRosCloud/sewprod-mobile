package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.models.Party;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyGetAllCallback implements Callback<List<Party>> {
    private final ListView listView;
    private final Context context;
    public PartyGetAllCallback(@NonNull Context context,@NonNull ListView listView){
        this.listView = listView;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<Party>> call, Response<List<Party>> response) {
        if(response.isSuccessful()){
            List<Party> sortedParties = new ArrayList<>();
            for (Party party: response.body()) {
                if(party.getDateEnd() == null){
                    sortedParties.add(party);
                }
            }
            ArrayAdapter<Party> adapter = new PartyAdapter(context, sortedParties);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<List<Party>> call, Throwable t) {

    }
}
