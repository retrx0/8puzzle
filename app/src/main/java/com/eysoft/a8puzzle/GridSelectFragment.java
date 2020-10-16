package com.eysoft.a8puzzle;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;



public class GridSelectFragment extends DialogFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    final CharSequence[] grid_select_items = {"3x3","4x4"};
    public static String selection = "3x3";
    Boolean selection_flag = false;
    MainActivity mainGameActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mainGameActivity = (MainActivity) this.getContext();
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
                        mainGameActivity.boardView.newPuzzle("123 456 780");
                        break;
                    case "4x4":
                        mainGameActivity.boardView.newPuzzle("1234 5678 9ABC DEF0");
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());

//        if ("grid_size".equals(key)){
//           pref.getString("")
//        }
    }

}