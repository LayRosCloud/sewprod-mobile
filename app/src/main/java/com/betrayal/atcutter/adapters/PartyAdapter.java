package com.betrayal.atcutter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.Party;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PartyAdapter extends ArrayAdapter<Party> {
    private final Context context;
    private final List<Party> parties;

    private final int SIMPLE_PARTY_ID;
    public PartyAdapter(@NonNull Context context, @NonNull List<Party> parties) {
        super(context, R.layout.simple_item_party, parties);

        this.context = context;
        this.parties = parties;

        SIMPLE_PARTY_ID = R.layout.simple_item_party;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(SIMPLE_PARTY_ID, parent, false);

        TextView shortName = (TextView) item.findViewById(R.id.shortName);
        TextView model = (TextView) item.findViewById(R.id.tvModel);
        TextView date = (TextView) item.findViewById(R.id.tvDate);

        Party currentParty = parties.get(position);

        shortName.setText(String.format("%s.%d/%s", currentParty.getCutNumber(), currentParty.getDateStart().getMonth()+1,currentParty.getPerson().getUid()));
        model.setText(currentParty.getModel().getTitle());
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        date.setText(formatter.format(currentParty.getDateStart()));
        return item;
    }
}
