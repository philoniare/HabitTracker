package com.example.philoniare.habittracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HabitViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.habit_name) TextView habitNameTV;
    @BindView(R.id.habit_count) TextView habitCountTV;
    @BindView(R.id.habit_increment_count) Button incrementCountBtn;
    private Utils.BtnClickListener mClickListener;
    private Context mContext;

    public HabitViewHolder(View itemView, Context mContext, Utils.BtnClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        this.mClickListener = listener;
    }

    @OnClick(R.id.habit_increment_count)
    public void incrementHabit(View view){
        if(mClickListener != null) {
            mClickListener.onBtnClick(view, getAdapterPosition());
        }
    }
}


