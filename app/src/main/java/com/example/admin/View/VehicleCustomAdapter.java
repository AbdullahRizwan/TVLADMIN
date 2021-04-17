package com.example.admin.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Model.Vehicle;
import com.example.admin.R;

import java.util.ArrayList;

public class VehicleCustomAdapter extends RecyclerView.Adapter<VehicleCustomAdapter.ViewHolder> {
    ArrayList<Vehicle> data;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_vehicle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.make.setText(data.get(position).getMake());
        holder.model.setText(data.get(position).getModel());
        holder.year.setText(data.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(MutableLiveData<ArrayList<Vehicle>> d) {
        data = d.getValue();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView model;
        TextView make;
        TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.model);
            make = itemView.findViewById(R.id.make);
            year = itemView.findViewById(R.id.year);
        }
    }
}

