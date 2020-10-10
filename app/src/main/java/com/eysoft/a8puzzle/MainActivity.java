package com.eysoft.a8puzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import AI.AStar;
import AI.HeuristicManhattan;
import AI.Solver;
import AI.SolverMemoDecorator;
import model.Board;
import view.BoardView;

public class MainActivity extends AppCompatActivity {

    GridLayout puzzleGrid;

    private static final String TAG = MainActivity.class.getSimpleName();

    public BoardView boardView;
//    public TextView moveView;
    public TextView goalView;
//    public Button newPuzzle;
    public Button solve;
//    public Button skipAhead;

    public Board goal;
    public Solver solver;

    // Set in boardView once view width is set
    public TranslateAnimation outBoard;
    public TranslateAnimation inBoard;

    private static final int DIALOG_ABOUT = 0;
    public static final int DIALOG_WIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boardView = (BoardView) findViewById(R.id.boardView);
//        moveView = (TextView) findViewById(R.id.moves);
//        goalView = (TextView) findViewById(R.id.goal);
//        newPuzzle = (Button) findViewById(R.id.newPuzzle);
        solve = (Button) findViewById(R.id.solveBtn);
//        skipAhead = (Button) findViewById(R.id.skipAhead);

        goal = new Board("123 456 780");
        solver = new SolverMemoDecorator(new AStar(goal, new HeuristicManhattan(goal)));
        setupBoardView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void setupBoardView() {
//        newPuzzle.setOnClickListener(boardView);
        solve.setOnClickListener(boardView);
//        skipAhead.setOnClickListener(boardView);
        boardView.setupPuzzle();
    }

    public void menuItemOnClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.settingsMenu:
                Intent intent = new Intent(this,SettingsActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.aboutMenu:
                Intent intent_about = new Intent(this,AboutActivity.class);
                MainActivity.this.startActivity(intent_about);
                break;
        }

        }
}