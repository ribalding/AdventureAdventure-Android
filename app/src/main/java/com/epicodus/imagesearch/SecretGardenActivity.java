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

import com.epicodus.imagesearch.ui.KitchenActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecretGardenActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.butterflyButton) Button mButterflyButton;
    @Bind(R.id.crownButton) Button mCrownButton;
    @Bind(R.id.heartButton) Button mHeartButton;
    @Bind(R.id.catsGameButton) Button mCatsGameButton;
    @Bind(R.id.squirrelButton) Button mSquirrelButton;
    @Bind(R.id.cucumberThingButton) Button mCucumber;
    @Bind(R.id.hintView) TextView mHintView;

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
        mCrownButton.setOnClickListener(this);
        mHeartButton.setOnClickListener(this);
        mCatsGameButton.setOnClickListener(this);
        mSquirrelButton.setOnClickListener(this);
        mCucumber.setOnClickListener(this);

        mTimerView = (TextView) findViewById(R.id.timerView);

        advance(youWin);

        timeElapsed = 0;

        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed ++;
                        mTimerView.setText(timeElapsed.toString());
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
            mCucumber.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mCucumber) {
            Toast.makeText(getApplicationContext(), "Is this even a cucumber?", Toast.LENGTH_SHORT).show();
            mHeartButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mHeartButton) {
            Toast.makeText(getApplicationContext(), "tree lover", Toast.LENGTH_SHORT).show();
            mCrownButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mCrownButton) {
            Toast.makeText(getApplicationContext(), "Tweet!", Toast.LENGTH_SHORT).show();
            mCatsGameButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mCatsGameButton) {
            Toast.makeText(getApplicationContext(), "Possibly an even worse game than this one", Toast.LENGTH_SHORT).show();
            mSquirrelButton.setOnClickListener(this);
            advance(youWin);
        }
        if (view == mSquirrelButton) {
            Toast.makeText(getApplicationContext(), "nut lover", Toast.LENGTH_SHORT).show();
            advance(youWin);
        }

        view.setOnClickListener(null);
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        timeElapsed += mSharedPreferences.getInt("timeScore", 1000);
        mEditor.putInt("timeScore", timeElapsed).apply();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        System.out.println(mSharedPreferences.getInt("timeScore", 1000));
        mTimer.cancel();
        Intent intent = new Intent(SecretGardenActivity.this, KitchenActivity.class);
        startActivity(intent);
    }

    private void advance(int stage) {
        if (stage == 6) {
            winFunction();
        } else {
            mHintView.setText(Constants.GARDEN_HINTS[stage]);
            youWin ++;
        }
    }
}
