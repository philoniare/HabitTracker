package com.example.philoniare.habittracker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddHabitDialog extends DialogFragment {

    public interface AddHabitDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String newHabitName);
    }
    AddHabitDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AddHabitDialogListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_habit, null);
        builder.setView(dialogView);
        final EditText habitNameET = (EditText) dialogView.findViewById(R.id.habitNameET);
        builder.setPositiveButton(R.string.action_add_habit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(AddHabitDialog.this,
                                habitNameET.getText().toString());
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddHabitDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
