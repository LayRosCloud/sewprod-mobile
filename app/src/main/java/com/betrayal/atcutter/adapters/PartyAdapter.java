package com.betrayal.atcutter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.PartyEntity;
import com.betrayal.atcutter.models.SizeEntity;

import java.util.List;

public class PartyAdapter extends ArrayAdapter<PartyEntity> {
    private final Context context;
    public PartyAdapter(@NonNull Context context, @NonNull List<PartyEntity> parties) {
        super(context, R.layout.party_item_list, parties);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getOnTemplate(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getOnTemplate(position,  parent);
    }
    private View getOnTemplate(int position, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.party_item_list, parent, false);

        PartyEntity party = getItem(position);

        TextView cutterNumber = view.findViewById(R.id.cutter_number);
        cutterNumber.setText(String.format("%s/%s", party.getCutNumber(), party.getPerson().getUid()));

        return view;
    }
}
