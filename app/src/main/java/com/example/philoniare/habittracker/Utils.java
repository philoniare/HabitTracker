package com.example.philoniare.habittracker;

import android.view.View;

public class Utils {
    public interface BtnClickListener {
        public abstract void onBtnClick(View view, int position);
    }
}
