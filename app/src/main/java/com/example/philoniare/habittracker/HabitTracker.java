package com.example.philoniare.habittracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.philoniare.habittracker.model.Habit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HabitTracker extends AppCompatActivity implements AddHabitDialog.AddHabitDialogListener {
    public List<Habit> habitList;
    public RecyclerView habitRecyclerView;
    public Realm realm;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);
        ButterKnife.bind(this);
        habitRecyclerView = (RecyclerView) findViewById(R.id.habit_list);
        setSupportActionBar(toolbar);

        // Initialize Realm db
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();

        // Initialize the list of habits from the db
        habitList = new ArrayList<>();
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HabitAdapter habitAdapter = new HabitAdapter(HabitTracker.this, habitList, new Utils.BtnClickListener() {
            @Override
            public void onBtnClick(View view, int position) {
                Habit habit = habitList.get(position);
                Habit storedHabit = realm.where(Habit.class)
                        .equalTo("name", habit.getName())
                        .findFirst();
                realm.beginTransaction();
                storedHabit.setCompletionCount(storedHabit.getCompletionCount() + 1);
                realm.commitTransaction();

                updateHabitListFromDB();
            }
        });
        habitRecyclerView.setAdapter(habitAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new AddHabitDialog();
                dialogFragment.show(getSupportFragmentManager(), "add_habit");
            }
        });
        updateHabitListFromDB();
    }

    public void updateHabitListFromDB() {
        habitList.clear();
        RealmResults<Habit> results = realm.where(Habit.class).findAll();
        for(Habit result: results) {
            habitList.add(result);
        }
        habitRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String newHabitName) {
        Habit newHabit = new Habit(newHabitName, 0);
        // Need to check if an object with the given name exists, we query later by name.
        //  Therefore, can't have duplicate entries with the same name.
        //  Also, enforced with @PrimaryKey in Realm
        RealmResults<Habit> results = realm.where(Habit.class)
                .equalTo("name", newHabitName).findAll();
        if (results.size() != 0) {
            final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);
            Snackbar.make(viewGroup, getString(R.string.habit_already_exists), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            // store to the database
            realm.beginTransaction();
            realm.copyToRealm(newHabit);
            realm.commitTransaction();
            updateHabitListFromDB();
        }
    }


}
