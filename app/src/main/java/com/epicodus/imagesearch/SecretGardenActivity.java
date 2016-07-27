package com.epicodus.imagesearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecretGardenActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.butterflyButton) Button mButterflyButton;
    @Bind(R.id.doveButton) Button mDoveButton;
    @Bind(R.id.heartButton) Button mHeartButton;
    @Bind(R.id.catsGameButton) Button mCatsGameButton;
    @Bind(R.id.squirrelButton) Button mSquirrelButton;
    @Bind(R.id.cucumberThingButton) Button mCucumber;
    private Integer youWin;
    private Integer winNumber;
    Timer mTimer;
    TimerTask task;
    Integer timeElapsed;
    private TextView mTimerView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_garden);
        ButterKnife.bind(this);
        youWin = 0;
        winNumber = 6;

        mButterflyButton.setOnClickListener(this);
        mDoveButton.setOnClickListener(this);
        mHeartButton.setOnClickListener(this);
        mCatsGameButton.setOnClickListener(this);
        mSquirrelButton.setOnClickListener(this);
        mCucumber.setOnClickListener(this);

        mTimerView = (TextView) findViewById(R.id.timerView);
        timeElapsed = 0;
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed --;
                        System.out.println(timeElapsed);
                        mTimerView.setText(timeElapsed.toString());
                        if (timeElapsed == 0) {
                            mTimer.cancel();
                            mTimer.purge();
                            Toast.makeText(getApplicationContext(), "FAILURE!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        };

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(task, 1000, 1000);
    }


    @Override
    public void onClick(View view) {
        if (view == mButterflyButton ) {
            Toast.makeText(getApplicationContext(), "Butterfly", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "Butterfly");
            mButterflyButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mDoveButton) {
            Toast.makeText(getApplicationContext(), "Tweet!", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "Dove");
            mDoveButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mHeartButton) {
            Toast.makeText(getApplicationContext(), "tree lover", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The heart.");
            mHeartButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mSquirrelButton) {
            Toast.makeText(getApplicationContext(), "nut lover", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The squirrel.");
            mHeartButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mCucumber) {
            Toast.makeText(getApplicationContext(), "Is this even a cucumber?", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The 'cuke.");
            mHeartButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mCatsGameButton) {
            Toast.makeText(getApplicationContext(), "Possibly an even worse game than this one", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The child's game.");
            mHeartButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        timeElapsed += mSharedPreferences.getInt("timeScore", 1000);
        mEditor.putInt("timeScore", timeElapsed).apply();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println(mSharedPreferences.getInt("timeScore", 1000));
        mTimer.cancel();
        Intent intent = new Intent(SecretGardenActivity.this, KitchenActivity.class);
        startActivity(intent);
    }
    }
}
