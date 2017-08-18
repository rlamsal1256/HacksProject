package com.project.libertyhacks.mutual.liberty.care;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.libertyhacks.mutual.liberty.care.models.Car;

import java.util.ArrayList;

/**
 * Created by n0312809 on 8/18/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<Car> list;

    public MyAdapter(ArrayList<Car> Data) {
        list = Data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
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