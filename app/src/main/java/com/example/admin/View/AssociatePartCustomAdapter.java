package com.example.admin.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Model.Part;
import com.example.admin.Model.Vehicle;
import com.example.admin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AssociatePartCustomAdapter extends RecyclerView.Adapter<AssociatePartCustomAdapter.ViewHolder> implements Filterable {

    private ArrayList<Part> data;
    protected ArrayList<Part> temp;
    private Context c;
    private Callback callback;

    @NonNull
    @Override
    public AssociatePartCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_part_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssociatePartCustomAdapter.ViewHolder holder, int position) {
        holder.bind(temp.get(position));
        if (temp.size() > position) {
            holder.name.setText(temp.get(position).getName());
            holder.type.setText(temp.get(position).getType());
        } else {
            Toast.makeText(c, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    // Callback interface, used to notify when an item's checked status changed
    public interface Callback {
        void onCheckedChanged(Part part, boolean isChecked);
    }



    public void setData(MutableLiveData<ArrayList<Part>> d, Context context) {
        data = d.getValue();
        temp = new ArrayList<>();
        temp.addAll(Objects.requireNonNull(d.getValue()));
        c = context;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.partName);
            type = itemView.findViewById(R.id.partType);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
        void bind(Part s) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Invoke the callback
                    if(callback != null) callback.onCheckedChanged(s, isChecked);
                }
            });
        }


    }

    @Override
    public Filter getFilter() {

        return exampleFiler;
    }

    Filter exampleFiler = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Part> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(data);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Part item : data) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            temp.clear();
            temp.addAll((List) results.values);
            notifyDataSetChanged();
        }


    };
}
