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
import com.betrayal.atcutter.models.Size;

import java.util.List;

public class SizeAdapter extends ArrayAdapter<Size> {
    private final List<Size> sizes;
    private LayoutInflater inflater;
    public SizeAdapter(@NonNull Context context, @NonNull List<Size> sizes) {
        super(context, R.layout.simple_item_party, sizes);

        this.sizes = sizes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.simple_item_size, parent, false);
        TextView label = view.findViewById(R.id.siz);
        label.setText(sizes.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View dropdownView = inflater.inflate(R.layout.simple_item_size, parent, false);
        TextView label = dropdownView.findViewById(R.id.siz);
        label.setText(sizes.get(position).getName());
        return dropdownView;
    }
}
