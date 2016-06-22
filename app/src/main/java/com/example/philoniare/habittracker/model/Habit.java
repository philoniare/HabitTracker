package com.example.philoniare.habittracker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Habit extends RealmObject {
    @PrimaryKey
    private String name;
    private int completionCount;

    public Habit() {}

    public Habit(String name, int completionCount) {
        this.name = name;
        this.completionCount = completionCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompletionCount() {
        return completionCount;
    }

    public void setCompletionCount(int completionCount) {
        this.completionCount = completionCount;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", completionCount=" + completionCount +
                '}';
    }
}
