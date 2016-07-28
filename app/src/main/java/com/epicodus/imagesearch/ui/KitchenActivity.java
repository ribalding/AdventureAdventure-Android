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

public class KitchenActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.coffeeButton) Button mCoffeeButton;
    @Bind(R.id.frogButton) Button mFrogButton;
    @Bind(R.id.starfishButton) Button mStarfishButton;
    @Bind(R.id.onionButton) Button mOnionButton;
    @Bind(R.id.pigButton) Button mPigButton;
    @Bind(R.id.strainerButton) Button mStrainerButton;
    @Bind(R.id.timerView) TextView mTimerView;
    @Bind(R.id.kitchenHintView) TextView mHintView;
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
        setContentView(R.layout.activity_kitchen);
        ButterKnife.bind(this);
        Typeface thunderFont = Typeface.createFromAsset(getAssets(), "fonts/Thunder Strike.ttf");
        mHintView.setTypeface(thunderFont);
        youWin = 0;
        winNumber = 6;
        advance(0);

        mFrogButton.setOnClickListener(this);

        mTimerView = (TextView) findViewById(R.id.timerView);
        timeElapsed = 0;

        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed ++;
//                        System.out.println(timeElapsed);
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
            mCoffeeButton.setOnClickListener(this);
            mArc_Progress.setProgress(63);
                advance(youWin);
        }
        if (view == mCoffeeButton) {
            mStarfishButton.setOnClickListener(this);
            mArc_Progress.setProgress(67);
                advance(youWin);
        }
        if (view == mStarfishButton) {
            mArc_Progress.setProgress(70);
            mOnionButton.setOnClickListener(this);
                advance(youWin);
        }
        if (view == mOnionButton){
            mStrainerButton.setOnClickListener(this);
            mArc_Progress.setProgress(73);
                advance(youWin);
        }
        if(view == mStrainerButton){
            mArc_Progress.setProgress(77);
            mPigButton.setOnClickListener(this);
                advance(youWin);
        }
        if(view == mPigButton){
            mArc_Progress.setProgress(80);
                advance(youWin);
        }
    }
    private void advance(int stage) {
        if (stage == 6) {
            winFunction();
        } else {
            mHintView.setText(Constants.KITCHEN_HINTS[stage]);
            youWin++;
        }
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Holy &%^# you win!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        timeElapsed += mSharedPreferences.getInt("timeScore", 1000);
        mEditor.putInt("timeScore", timeElapsed).apply();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mTimer.cancel();
        Intent intent = new Intent(KitchenActivity.this, AtticActivity.class);
        startActivity(intent);
    }
}
