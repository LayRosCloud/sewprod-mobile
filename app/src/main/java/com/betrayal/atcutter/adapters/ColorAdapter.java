package com.betrayal.atcutter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.Color;
import com.betrayal.atcutter.models.Material;

import java.util.List;

public class ColorAdapter extends ArrayAdapter<Color> {
    private final List<Color> colors;
    private LayoutInflater inflater;
    public ColorAdapter(@NonNull Context context, @NonNull List<Color> colors) {
        super(context, R.layout.simple_item_color, colors);

        this.colors = colors;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.simple_item_color, parent, false);
        TextView label = view.findViewById(R.id.col);
        label.setText(colors.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View dropdownView = inflater.inflate(R.layout.simple_item_color, parent, false);
        TextView label = dropdownView.findViewById(R.id.col);
        label.setText(colors.get(position).getName());
        return dropdownView;
    }
}
