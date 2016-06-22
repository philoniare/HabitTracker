package com.example.philoniare.habittracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public class HabitViewHolder extends RecyclerView.ViewHolder {
    TextView articleTitle;
    private Context mContext;

    public HabitViewHolder(View itemView, Context mContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
    }
}


