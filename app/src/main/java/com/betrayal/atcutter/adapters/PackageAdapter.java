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
import com.betrayal.atcutter.models.Package;

import java.util.List;

public class PackageAdapter extends ArrayAdapter<Package> {
    private final Context context;
    private final List<Package> packages;

    private final int SIMPLE_PACKAGE_ID;
    public PackageAdapter(@NonNull Context context, @NonNull List<Package> packages) {
        super(context, R.layout.simple_item_package, packages);

        this.context = context;
        this.packages = packages;

        SIMPLE_PACKAGE_ID = R.layout.simple_item_package;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(SIMPLE_PACKAGE_ID, parent, false);

        TextView tvCount = (TextView) item.findViewById(R.id.tvCount);
        TextView tvMaterial = (TextView) item.findViewById(R.id.tvMaterial);
        TextView tvSize = (TextView) item.findViewById(R.id.tvSize);
        TextView tvColor = (TextView) item.findViewById(R.id.tvColor);

        Package currentPackage = packages.get(position);

        tvCount.setText(String.valueOf(currentPackage.getCount()));
        tvMaterial.setText(currentPackage.getMaterial().getName());
        tvSize.setText(currentPackage.getSize().getName());
        tvColor.setText(currentPackage.getColor().getName());

        return item;
    }
}
