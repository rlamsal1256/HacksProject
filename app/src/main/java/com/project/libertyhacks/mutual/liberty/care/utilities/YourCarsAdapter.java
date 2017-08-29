package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;

import java.util.ArrayList;

/**
 * Created by n0312809 on 8/18/2017.
 */

public class YourCarsAdapter extends RecyclerView.Adapter<YourCarsAdapter.MyViewHolder> {
    private ArrayList<Car> list;
    private String totalSteps;

    public YourCarsAdapter(ArrayList<Car> data, String steps) {
        list = data;
        totalSteps = steps;
    }

    public void updateSteps(String steps){
        totalSteps = steps;
        this.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carNameTextView;
        TextView carMilesTextView;

        MyViewHolder(View v) {
            super(v);
            carNameTextView =  v.findViewById(R.id.carNameTextView);
            carMilesTextView = v.findViewById(R.id.carMilesTextView);
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

//        String miles = String.valueOf(list.get(position).getMiles()) + " miles";
        String miles = String.valueOf(totalSteps) + " miles";

        holder.carNameTextView.setText(list.get(position).getName());
        holder.carMilesTextView.setText(miles);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}