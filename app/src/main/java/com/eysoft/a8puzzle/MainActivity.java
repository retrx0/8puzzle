package com.eysoft.a8puzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    GridLayout puzzleGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puzzleGrid = findViewById(R.id.puzzleGrid);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        puzzleGrid.setColumnCount(3);
        puzzleGrid.setRowCount(4);
//        puzzleGrid.setAlignmentMode(GridLayout.FILL);
        for (int i =1; i<9; i++){
            Button btn = new Button(this);
            btn.setText(""+i);
            btn.setHeight(20);
            btn.setWidth(20);
            puzzleGrid.addView(btn);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.);
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void solveButtonOnClick(View view) {

    }
}