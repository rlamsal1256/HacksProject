package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.activities.RemoveCarActivity;
import com.project.libertyhacks.mutual.liberty.care.models.Car;

import java.util.ArrayList;

/**
 * Created by n0312809 on 8/18/2017.
 */
public class YourCarsAdapter extends RecyclerView.Adapter<YourCarsAdapter.MyViewHolder> {
    private ArrayList<Car> list;
    private String totalSteps;
    private Activity context;

    public YourCarsAdapter(Activity context, ArrayList<Car> data, String steps) {
        this.context = context;
        list = data;
        totalSteps = steps;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carNameTextView;
        TextView carMilesTextView;

        MyViewHolder(View v) {
            super(v);
            carNameTextView =  v.findViewById(R.id.carNameTextView);
            carMilesTextView = v.findViewById(R.id.carMilesTextView);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RemoveCarActivity.class);
                    Bundle b = new Bundle();
                    b.putString("carName", carNameTextView.getText().toString());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
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