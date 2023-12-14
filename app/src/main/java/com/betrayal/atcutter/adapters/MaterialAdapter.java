package com.betrayal.atcutter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.Material;

import java.util.List;

public class MaterialAdapter extends ArrayAdapter<Material> {
    private final List<Material> materials;
    private LayoutInflater inflater;
    public MaterialAdapter(@NonNull Context context, @NonNull List<Material> materials) {
        super(context, R.layout.simple_item_color, materials);

        this.materials = materials;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.simple_item_material, parent, false);
        TextView label = view.findViewById(R.id.mat);
        label.setText(materials.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View dropdownView = inflater.inflate(R.layout.simple_item_material, parent, false);
        TextView label = dropdownView.findViewById(R.id.mat);
        label.setText(materials.get(position).getName());
        return dropdownView;
    }
}
