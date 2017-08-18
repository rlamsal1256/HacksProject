package com.project.libertyhacks.mutual.liberty.care;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by n0312809 on 8/18/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView carNameTextView;
    public TextView carMilestextView;

    public MyViewHolder(View v) {
        super(v);
        carNameTextView =  v.findViewById(R.id.carNameTextView);
        carMilestextView = v.findViewById(R.id.carMilesTextView);
    }
}