package com.epicodus.imagesearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.imagesearch.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrophyRoomActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.eyeButton) Button mEyeButton;
    @Bind(R.id.hatButton) Button mHatButton;
    @Bind(R.id.handprintButton) Button mHandprintButton;
    private Integer youWin;
    private Integer winNumber;
    Timer mTimer;
    TimerTask task;
    Integer timeRemaining;
    private TextView mTimerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_room);
        ButterKnife.bind(this);
        youWin = 0;
        winNumber = 3;

        mEyeButton.setOnClickListener(this);
        mHatButton.setOnClickListener(this);
        mHandprintButton.setOnClickListener(this);

        mTimerView = (TextView) findViewById(R.id.timerView);
        timeRemaining = 10;
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
        if (view == mEyeButton ) {
            Toast.makeText(getApplicationContext(), "OW MY EYE", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The muthafuckin eye");
            mEyeButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mHatButton) {
            Toast.makeText(getApplicationContext(), "topothemornintoya", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The sweet-ass hat.");
            mHatButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
        if (view == mHandprintButton) {
            Toast.makeText(getApplicationContext(), "Very ominous", Toast.LENGTH_SHORT).show();
            Log.d("You clicked: ", "The handprint.");
            mHandprintButton.setOnClickListener(null);
            youWin ++;
            if (youWin.equals(winNumber)) {
                winFunction();
            }
        }
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mTimer.cancel();
    }
}
