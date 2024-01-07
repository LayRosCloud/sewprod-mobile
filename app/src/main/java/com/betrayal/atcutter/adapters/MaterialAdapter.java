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
import com.betrayal.atcutter.models.MaterialEntity;
import com.betrayal.atcutter.models.PartyEntity;

import java.util.List;

public class MaterialAdapter extends ArrayAdapter<MaterialEntity> {
    private final Context context;
    public MaterialAdapter(@NonNull Context context, @NonNull List<MaterialEntity> materials) {
        super(context, R.layout.material_item_list, materials);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getOnTemplate(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getOnTemplate(position, parent);
    }

    private View getOnTemplate(int position, @NonNull ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.material_item_list, parent, false);

        MaterialEntity material = getItem(position);

        TextView name = view.findViewById(R.id.name);
        name.setText(material.getName());
        return view;
    }
}
