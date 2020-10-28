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



public class GridSelectFragment extends DialogFragment{

    final CharSequence[] grid_select_items = {"3x3","4x4"};
    public static String selection = "3x3";
    public final static String gridSizePrefKey = "grid_size";

    Boolean selection_flag = false;
    MainActivity mainGameActivity;
    SharedPreferences preferences;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String sp = preferences.getString(gridSizePrefKey, "3x3");
        int cp;
        assert sp != null;
        if (sp.equals("3x3")) cp =0;
        else cp = 1;

        mainGameActivity = (MainActivity) this.getContext();
        return new AlertDialog.Builder(getActivity()).setTitle("Choose Grid").setSingleChoiceItems(grid_select_items, cp, new DialogInterface.OnClickListener() {
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
                    default:
                        selection  = "3x3";
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

}