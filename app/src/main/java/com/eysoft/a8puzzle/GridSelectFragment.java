package com.eysoft.a8puzzle;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;



public class GridSelectFragment extends DialogFragment {

    final CharSequence[] grid_select_items = {"3x3","4x4"};
    String selection = "";
    Boolean selection_flag = false;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity()).setTitle("Choose Grid").setSingleChoiceItems(grid_select_items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        selection = "3x3";
                        selection_flag = true;
                        break;
                    case 1:
                        selection = "4x4";
                        selection_flag = true;
                        break;
                }
            }
        }).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (selection){
                    case "3x3":

                        break;
                    case "4x4":

                        break;
                    default:
                        dismiss();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        }).create();
    }
}