package com.example.philoniare.habittracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.philoniare.habittracker.model.Habit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class HabitViewHolder extends RecyclerView.ViewHolder {
    public TextView habitNameTV;
    public TextView habitCountTV;
    public Button incrementCountBtn;
    private Context mContext;

    public HabitViewHolder(View itemView, Context mContext) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        habitNameTV = (TextView) itemView.findViewById(R.id.habit_name);
        habitCountTV = (TextView) itemView.findViewById(R.id.habit_count);
        incrementCountBtn = (Button) itemView.findViewById(R.id.habit_increment_count);
    }

    @OnClick(R.id.habit_increment_count)
    public void incrementHabit(View view){
        Habit habit = HabitTracker.habitList.get(getAdapterPosition());
        Realm realm = Realm.getDefaultInstance();
        Habit storedHabit = realm.where(Habit.class)
                .equalTo("name", habit.getName())
                .findFirst();
        realm.beginTransaction();
        storedHabit.setCompletionCount(storedHabit.getCompletionCount() + 1);
        realm.commitTransaction();
        HabitTracker.updateHabitListFromDB();
    }
}


