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
import com.betrayal.atcutter.models.PackageEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PackageAdapter extends ArrayAdapter<PackageEntity> {
    private final Context context;
    private final List<PackageEntity> packages;

    private final int SIMPLE_PACKAGE_ID;
    public PackageAdapter(@NonNull Context context, @NonNull List<PackageEntity> packages) {
        super(context, R.layout.pack_item_list, packages);

        this.context = context;
        this.packages = packages;

        SIMPLE_PACKAGE_ID = R.layout.pack_item_list;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(SIMPLE_PACKAGE_ID, parent, false);

        TextView cutterNumber = item.findViewById(R.id.cutter_number);
        TextView cutterDate = item.findViewById(R.id.date_cutter);
        TextView size = item.findViewById(R.id.size);
        TextView categoryOfSize = item.findViewById(R.id.category_size);

        PackageEntity currentPackage = packages.get(position);

        String formattedCutterNumber =
                String.format("%s/%s", currentPackage.getParty().getCutNumber(),
                        currentPackage.getParty().getPerson().getUid());
        cutterNumber.setText(formattedCutterNumber);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String formattingDate = dateFormat.format(currentPackage.getParty().getDateStart());
        cutterDate.setText(formattingDate);

        size.setText(currentPackage.getSize().getNumber());
        categoryOfSize.setText(currentPackage.getSize().getAge().getName());
        return item;
    }
}
