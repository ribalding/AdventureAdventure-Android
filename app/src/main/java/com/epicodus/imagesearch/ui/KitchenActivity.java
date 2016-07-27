package com.epicodus.imagesearch.ui;

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

import com.epicodus.imagesearch.R;
import com.epicodus.imagesearch.SecretGardenActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KitchenActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.coffeeButton) Button mCoffeeButton;
    @Bind(R.id.frogButton) Button mFrogButton;
    @Bind(R.id.starfishButton) Button mStarfishButton;
    @Bind(R.id.onionButton) Button mOnionButton;
    @Bind(R.id.pigButton) Button mPigButton;
    @Bind(R.id.strainerButton) Button mStrainerButton;
    @Bind(R.id.timerView) TextView mTimerView;
    private Integer youWin;
    private Integer winNumber;
    Timer mTimer;
    TimerTask task;
    Integer timeElapsed;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        ButterKnife.bind(this);
        youWin = 0;
        winNumber = 6;

        mCoffeeButton.setOnClickListener(this);
        mFrogButton.setOnClickListener(this);
        mStarfishButton.setOnClickListener(this);
        mPigButton.setOnClickListener(this);
        mOnionButton.setOnClickListener(this);
        mStrainerButton.setOnClickListener(this);
        mTimerView = (TextView) findViewById(R.id.timerView);
        timeElapsed = 0;

        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed ++;
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
        if (view == mFrogButton ) {
            Toast.makeText(getApplicationContext(), "Ribbit", Toast.LENGTH_SHORT).show();
            mFrogButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber) && timeElapsed != 0) {
                winFunction();
            }
        }
        if (view == mCoffeeButton) {
            Toast.makeText(getApplicationContext(), "Precious caffeine", Toast.LENGTH_SHORT).show();
            mCoffeeButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber) && timeElapsed != 0) {
                winFunction();
            }
        }
        if (view == mStarfishButton) {
            Toast.makeText(getApplicationContext(), "Twinkle twinkle", Toast.LENGTH_SHORT).show();
            mStarfishButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber) && timeElapsed != 0) {
                winFunction();
            }
        }
        if (view == mOnionButton){
            Toast.makeText(getApplicationContext(), "Layers upon layers of oniony goodness", Toast.LENGTH_SHORT).show();
            mOnionButton.setOnClickListener(null);
            youWin ++;
            if(youWin.equals(winNumber) && timeElapsed != 0){
                winFunction();
            }
        }

        if(view == mStrainerButton){
            Toast.makeText(getApplicationContext(), "I hope this game isn't too...straining", Toast.LENGTH_SHORT).show();
            mStrainerButton.setOnClickListener(null);
            youWin ++;
            if(youWin.equals(winNumber) && timeElapsed != 0){
                winFunction();
            }
        }

        if(view == mPigButton){
            Toast.makeText(getApplicationContext(), "Oink", Toast.LENGTH_SHORT).show();
            mPigButton.setOnClickListener(null);
            youWin ++;
            if(youWin.equals(winNumber) && timeElapsed != 0){
                winFunction();
            }
        }

    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt("timeScore", timeElapsed).apply();
        System.out.println(mSharedPreferences.getInt("timeScore", 1000));
        mTimer.cancel();
        Intent intent = new Intent(KitchenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
