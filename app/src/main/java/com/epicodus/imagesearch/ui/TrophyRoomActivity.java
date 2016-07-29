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

public class TrophyRoomActivity extends AppCompatActivity implements View.OnClickListener{


    @Bind(R.id.eyeButton) Button mEyeButton;
    @Bind(R.id.hatButton) Button mHatButton;
    @Bind(R.id.handprintButton) Button mHandprintButton;
    @Bind(R.id.yarnButton) Button mYarnButton;
    @Bind(R.id.pinsButton) Button mPinsButton;
    @Bind(R.id.hookButton) Button mHookButton;
    @Bind(R.id.timerView) TextView mTimerView;
    @Bind(R.id.hintView) TextView mHintView;
    @Bind(R.id.arc_progress) ArcProgress mArc_Progress;

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
        setContentView(R.layout.activity_trophy_room);
        ButterKnife.bind(this);
        Typeface carletonFont = Typeface.createFromAsset(getAssets(), "fonts/Carleton.ttf");
        mHintView.setTypeface(carletonFont);
        youWin = 0;
        winNumber = 6;

        mEyeButton.setOnClickListener(this);
        mTimerView = (TextView) findViewById(R.id.timerView);
        timeElapsed = 0;

        advance(youWin);
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed ++;
                        mTimerView.setText(timeElapsed.toString());
                        if (timeElapsed == 0) {
                            mTimer.cancel();
                            mTimer.purge();
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
            mHandprintButton.setOnClickListener(this);
            mArc_Progress.setProgress(3);
            advance(youWin);
        }
        if (view == mHandprintButton) {
            mHatButton.setOnClickListener(this);
            mArc_Progress.setProgress(7);
            advance(youWin);
        }
        if (view == mHatButton) {
            mYarnButton.setOnClickListener(this);
            mHintView.setPadding(0, 9, 0, 0);
            mHintView.setTextSize(13);
            mArc_Progress.setProgress(10);
            advance(youWin);
        }
        if (view == mYarnButton){
            mHookButton.setOnClickListener(this);
            mHintView.setPadding(0, 0, 0, 0);
            mHintView.setTextSize(15);
            mArc_Progress.setProgress(13);
            advance(youWin);
        }
        if(view == mHookButton){
            mPinsButton.setOnClickListener(this);
            mArc_Progress.setProgress(16);
            advance(youWin);
        }
        if(view == mPinsButton){
            mArc_Progress.setProgress(20);
            advance(youWin);
        }
        view.setOnClickListener(null);
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Your time for this level is " + timeElapsed + " seconds!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt("timeScore", timeElapsed).apply();
        mTimer.cancel();
        Intent intent = new Intent(TrophyRoomActivity.this, EgyptActivity.class);
        startActivity(intent);
    }
    private void advance(int stage) {
        if (stage == 6) {
            winFunction();
        } else {
            mHintView.setText(Constants.TROPHY_HINTS[stage]);
            youWin ++;
        }
    }
}
