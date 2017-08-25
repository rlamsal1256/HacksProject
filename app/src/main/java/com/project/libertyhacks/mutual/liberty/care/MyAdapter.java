package com.project.libertyhacks.mutual.liberty.care;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.libertyhacks.mutual.liberty.care.models.Car;

import java.util.ArrayList;

/**
 * Created by n0312809 on 8/18/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Car> list;

    public MyAdapter(ArrayList<Car> Data) {
        list = Data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView carNameTextView;
        public TextView carMilestextView;

        public MyViewHolder(View v) {
            super(v);
            carNameTextView =  v.findViewById(R.id.carNameTextView);
            carMilestextView = v.findViewById(R.id.carMilesTextView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.carNameTextView.setText(list.get(position).getName());
        holder.carMilestextView.setText(list.get(position).getMiles());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}