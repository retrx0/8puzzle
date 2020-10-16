package com.eysoft.a8puzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import AI.AStar;
import AI.HeuristicManhattan;
import AI.Solver;
import AI.SolverMemoDecorator;
import model.Board;
import view.BoardView;
import view.Piece;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    GridLayout puzzleGrid;

    private static final String TAG = MainActivity.class.getSimpleName();

    public BoardView boardView;
    public TextView moveView;
    public TextView goalView;
    public Button shuffle;
    public Button solve;
//    public Button skipAhead;

    public TextView timerTextView;
    public Board goal;
    public Solver solver;

    // Set in boardView once view width is set
    public TranslateAnimation outBoard;
    public TranslateAnimation inBoard;

    private static final int DIALOG_ABOUT = 0;
    public static final int DIALOG_WIN = 1;

    private static final float SHAKE_THRESHOLD = 3.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private SensorManager mSensorMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boardView = (BoardView) findViewById(R.id.boardView);
        moveView = (TextView) findViewById(R.id.moves);
        goalView = (TextView) findViewById(R.id.goal);
        shuffle = (Button) findViewById(R.id.shuffle);
        solve = (Button) findViewById(R.id.solveBtn);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        //        skipAhead = (Button) findViewById(R.id.skipAhead);
        goal = new Board("123 456 780");
        solver = new SolverMemoDecorator(new AStar(goal, new HeuristicManhattan(goal)));
        setupBoardView();

        // Get a sensor manager to listen for shakes
        mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Listen for shakes
        Sensor accelerometer = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorMgr.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        int nightModeFlags = getBaseContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.DarkTheme);
                Piece.colors = new int[]{Color.rgb(32, 32, 32), Color.rgb(32, 32, 32)};
                Piece.shadowColor = Color.rgb(22,22,22);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.AppTheme);
                Piece.shadowColor = Color.rgb(197,197,197);
                Piece.colors = new int[]{Color.rgb(0, 191, 255), Color.rgb(0, 191, 255)};
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                if(findViewById(R.id.darkModeSwitchPref).isSelected()){
//                    setTheme(R.style.DarkTheme);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    Piece.shadowColor = Color.rgb(22,22,22);
//                    Piece.colors = new int[]{Color.rgb(32, 32, 32), Color.rgb(32, 32, 32)};
//                }
//                else{
//                    setTheme(R.style.AppTheme);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    Piece.shadowColor = Color.rgb(197,197,197);
//                    Piece.colors = new int[]{Color.rgb(0, 191, 255), Color.rgb(0, 191, 255)};
//                }

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Resuming activity...");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing activity...");
        super.onPause();
        boardView.pause();
    }

    public void setupBoardView() {
        shuffle.setOnClickListener(boardView);
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

    public void showMyDialog(int dialog) {
        switch (dialog){
            case DIALOG_ABOUT:
                break;
            case DIALOG_WIN:
                DialogFragment win = new WinFragment();
                win.show(getSupportFragmentManager(), "Win Fragment");
                break;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    Log.d("8puzzle", "Shake, Rattle, and Roll");
                    Toast.makeText(this, "Shake Detected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void gridSizeChangeOnClick(MenuItem item) {
        DialogFragment grid_select = new GridSelectFragment();
        grid_select.show(getSupportFragmentManager(), "Grid Select");
    }

}