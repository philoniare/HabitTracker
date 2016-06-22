package com.example.philoniare.habittracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.philoniare.habittracker.model.Habit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HabitTracker extends AppCompatActivity {
    public static List<Habit> habitList;
    @BindView(R.id.habit_list) RecyclerView habitRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // Initialize Realm db
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();

        // Initialize the list of habits from the db
        habitList = new ArrayList<>();
        habitList = realm.where(Habit.class).findAll();

        habitRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HabitAdapter habitAdapter = new HabitAdapter(HabitTracker.this, habitList);
        habitRecyclerView.setAdapter(habitAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new AddHabitDialog();
                dialogFragment.show(getSupportFragmentManager(), "add_habit");
            }
        });




    }
}
