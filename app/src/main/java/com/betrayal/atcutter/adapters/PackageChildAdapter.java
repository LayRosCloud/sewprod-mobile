package com.betrayal.atcutter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.models.PackageEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PackageChildAdapter extends RecyclerView.Adapter<PackageChildAdapter.ViewHolder> {

    private final List<PackageEntity> packages;

    public PackageChildAdapter(List<PackageEntity> packages){
       this.packages = packages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.pack_item_list, parent, false);
        return new PackageChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PackageEntity currentPackage = packages.get(position);

        String formattedCutterNumber =
                String.format("%s/%s", currentPackage.getParty().getCutNumber(),
                        currentPackage.getParty().getPerson().getUid());
        holder.cutterNumber.setText(formattedCutterNumber);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String formattingDate = dateFormat.format(currentPackage.getParty().getDateStart());
        holder.dateCutter.setText(formattingDate);

        holder.size.setText(currentPackage.getSize().getNumber());
        holder.categorySize.setText(currentPackage.getSize().getAge().getName());
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView cutterNumber;
        private final TextView dateCutter;
        private final TextView size;
        private final TextView categorySize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cutterNumber = itemView.findViewById(R.id.cutter_number);
            dateCutter = itemView.findViewById(R.id.date_cutter);
            size = itemView.findViewById(R.id.size);
            categorySize = itemView.findViewById(R.id.category_size);
        }
    }
}
