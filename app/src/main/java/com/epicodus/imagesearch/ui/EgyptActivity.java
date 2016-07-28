package com.epicodus.imagesearch.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.epicodus.imagesearch.Constants;
import com.epicodus.imagesearch.R;
import com.epicodus.imagesearch.ui.SecretGardenActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;


import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EgyptActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer youWin;
    private Integer winNumber;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    Timer mTimer;
    TimerTask task;
    Integer timeElapsed;
    private TextView mTimerView;
    @Bind(R.id.eyesButton) Button mEyesButton;
    @Bind(R.id.dragonButton) Button mDragonButton;
    @Bind(R.id.binoButton) Button mBinoButton;
    @Bind(R.id.purseButton) Button mPurseButton;
    @Bind(R.id.spiderButton) Button mSpiderButton;
    @Bind(R.id.fishButton) Button mFishButton;
    @Bind(R.id.hintView) TextView mHintView;
    @Bind(R.id.arc_progress) ArcProgress mArc_Progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egypt);
        ButterKnife.bind(this);
        Typeface thunderFont = Typeface.createFromAsset(getAssets(), "fonts/Thunder Strike.ttf");
        mHintView.setTypeface(thunderFont);
        youWin = 0;
        winNumber = 6;

        mSpiderButton.setOnClickListener(this);


        mTimerView = (TextView) findViewById(R.id.timerView);
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
        advance(youWin);
    }

    @Override
    public void onClick(View view) {

        if(view == mSpiderButton) {
            advance(youWin);
            mEyesButton.setOnClickListener(this);
            mArc_Progress.setProgress(23);
        }

        if(view == mEyesButton) {
            advance(youWin);
            mDragonButton.setOnClickListener(this);
            mArc_Progress.setProgress(27);
        }

        if (view == mDragonButton){
            advance(youWin);
            mBinoButton.setOnClickListener(this);
            mArc_Progress.setProgress(30);
        }

        if(view == mBinoButton) {
            advance(youWin);
            mFishButton.setOnClickListener(this);
            mArc_Progress.setProgress(33);
        }

        if(view == mFishButton) {
            advance(youWin);
            mPurseButton.setOnClickListener(this);
            mArc_Progress.setProgress(37);
        }

        if(view == mPurseButton) {
            mPurseButton.setOnClickListener(null);
            mArc_Progress.setProgress(40);
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
        mTimer.cancel();
        Intent intent = new Intent(EgyptActivity.this, SecretGardenActivity.class);
        startActivity(intent);
    }

    private void advance(int stage) {
        if (stage == 6) {
            winFunction();
        } else {

            mHintView.setText(Constants.EGYPT_HINTS[stage]);
            youWin++;
        }

    }
}
