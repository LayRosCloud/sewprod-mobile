package com.betrayal.atcutter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.scripts.model.SectionPackage;

import java.util.List;

public class PartyHeaderAdapter extends RecyclerView.Adapter<PartyHeaderAdapter.ViewHolder> {

    private final List<SectionPackage> list;

    public PartyHeaderAdapter(List<SectionPackage> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.header_party_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionPackage section = list.get(position);
        holder.title.setText(section.getHeader());
        PackageChildAdapter adapter = new PackageChildAdapter(section.getPackageEntities());
        holder.childRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final RecyclerView childRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            childRecyclerView = itemView.findViewById(R.id.child_recycler_view);
        }
    }
}
