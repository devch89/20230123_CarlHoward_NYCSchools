package com.example.a20230123_carlhoward_nycschools.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20230123_carlhoward_nycschools.R;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolResponse;

import java.util.List;

public class HighSchoolAdapter extends RecyclerView.Adapter<HighSchoolAdapter.HSchoolViewHolder> {

    private List<HighSchoolResponse> dataSet;
    private Listeners.ListClickEvent clickEvent;

    public HighSchoolAdapter(List<HighSchoolResponse> dataSet,
                             Listeners.ListClickEvent clickEvent){
        this.dataSet = dataSet;
        this.clickEvent = clickEvent;
    }

    @NonNull
    @Override
    public HSchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HSchoolViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.hschool_item_layout,
                                parent,
                                false
                        )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HSchoolViewHolder holder, int position) {
        holder.bind(
                dataSet.get(position),
                clickEvent
        );
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public class HSchoolViewHolder extends RecyclerView.ViewHolder {

        private TextView hSchoolName;

        public HSchoolViewHolder(@NonNull View itemView) {
            super(itemView);
            hSchoolName = itemView.findViewById(R.id.hschool_item_school_name);
        }

        public void bind(HighSchoolResponse dataItem, Listeners.ListClickEvent clickEvent) {
            hSchoolName.setText(
                    dataItem.getSchool_name()
            );
            itemView.setOnClickListener(view->
                    clickEvent.clickDetails(dataItem.getDbn(), dataItem.getSchool_name(), dataItem.getLocation(), dataItem.getSchool_email(), dataItem.getPhone_number())
            );
        }
    }
}
