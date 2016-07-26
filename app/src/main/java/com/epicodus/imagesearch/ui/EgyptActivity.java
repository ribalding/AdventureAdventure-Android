package com.epicodus.imagesearch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.imagesearch.Constants;
import com.epicodus.imagesearch.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EgyptActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer youWin;
    private Integer winNumber;

    Timer mTimer;
    TimerTask task;
    Integer timeRemaining;
    private TextView mTimerView;
    @Bind(R.id.eyesButton) Button mTestButton;
    @Bind(R.id.dragonButton) Button mDragonButton;
    @Bind(R.id.binoButton) Button mBinoButton;
    @Bind(R.id.purseButton) Button mPurseButton;
    @Bind(R.id.spiderButton) Button mSpiderButton;
    @Bind(R.id.fishButton) Button mFishButton;
    @Bind(R.id.hintView) TextView mHintView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egypt);
        ButterKnife.bind(this);
        youWin = 0;
        winNumber = 6;
        mTestButton.setOnClickListener(this);
        mDragonButton.setOnClickListener(this);
        mSpiderButton.setOnClickListener(this);
        mPurseButton.setOnClickListener(this);
        mFishButton.setOnClickListener(this);
        mBinoButton.setOnClickListener(this);

        mTimerView = (TextView) findViewById(R.id.timerView);
        timeRemaining = 60;

        advance(youWin);
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeRemaining --;
                        System.out.println(timeRemaining);
                        mTimerView.setText(timeRemaining.toString());
                        if (timeRemaining == 0) {
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
        if(view == mTestButton) {
            Log.d("Winning", "sehn");
            mTestButton.setOnClickListener(null);
            advance(youWin);
        }

        if (view == mDragonButton){
            Log.d("Dragons", "everywhere");
            mDragonButton.setOnClickListener(null);
            advance(youWin);
        }

        if(view == mBinoButton) {
            Log.d("Bino", "Win");
            mBinoButton.setOnClickListener(null);
            advance(youWin);
        }

        if(view == mFishButton) {
            Log.d("Fish", "Just Keep Swimming");
            mFishButton.setOnClickListener(null);
            advance(youWin);
        }

        if(view == mPurseButton) {
            Log.d("purse", "Purse button...");
            mPurseButton.setOnClickListener(null);
            advance(youWin);
        }

        if(view == mSpiderButton) {
            Log.d("Spider", "Ahhh!");
            mSpiderButton.setOnClickListener(null);
            advance(youWin);
        }
    }
    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mTimer.cancel();
    }

    private void advance(int stage) {
        if (stage == 6 && timeRemaining != 0) {
            winFunction();
        } else if (timeRemaining != 0) {
            mHintView.setText(Constants.EGYPT_HINTS[stage]);
            youWin ++;
        }
    }
}
