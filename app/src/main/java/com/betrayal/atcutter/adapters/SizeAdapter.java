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
import com.betrayal.atcutter.models.SizeEntity;

import java.util.List;

public class SizeAdapter extends ArrayAdapter<SizeEntity> {
    private final Context context;
    public SizeAdapter(@NonNull Context context, List<SizeEntity> sizes) {
        super(context, R.layout.size_item_list, sizes);
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

    private View getOnTemplate(int position, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.size_item_list, parent, false);

        SizeEntity size = getItem(position);

        TextView sizeText = view.findViewById(R.id.size);
        TextView category = view.findViewById(R.id.category_size);

        sizeText.setText(size.getNumber());
        category.setText(size.getAge().getName());

        return view;
    }
}
