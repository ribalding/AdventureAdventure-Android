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
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AtticActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.mustacheButton) Button mMustacheButton;
    @Bind(R.id.ratButton) Button mRatButton;
    @Bind(R.id.fingerPrintButton) Button mFingerPrint;
    @Bind(R.id.dynamiteButton) Button mDynamiteButton;
    @Bind(R.id.cameraButton) Button mCameraButton;
    @Bind(R.id.cigarButton) Button mCigarButton;
    @Bind(R.id.arc_progress) ArcProgress mArc_Progress;
    @Bind(R.id.atticHintView) TextView mHintView;

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
        setContentView(R.layout.activity_attic);
        ButterKnife.bind(this);
        Typeface thunderFont = Typeface.createFromAsset(getAssets(), "fonts/Thunder Strike.ttf");
        mHintView.setTypeface(thunderFont);
        youWin = 0;
        winNumber = 6;

        mMustacheButton.setOnClickListener(this);

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
        if (view == mMustacheButton ) {
            mRatButton.setOnClickListener(this);
            mArc_Progress.setProgress(83);
            advance(youWin);
        }
        if (view == mRatButton) {
            mFingerPrint.setOnClickListener(this);
            mArc_Progress.setProgress(87);
            advance(youWin);
        }
        if (view == mFingerPrint) {
            mDynamiteButton.setOnClickListener(this);
            mArc_Progress.setProgress(90);
            advance(youWin);
        }
        if (view == mDynamiteButton) {
            mCameraButton.setOnClickListener(this);
            mArc_Progress.setProgress(93);
            advance(youWin);
        }
        if (view == mCameraButton) {
            mCigarButton.setOnClickListener(this);
            mArc_Progress.setProgress(97);
            advance(youWin);
        }
        if (view == mCigarButton) {
            mArc_Progress.setProgress(100);
            advance(youWin);
        }

        view.setOnClickListener(null);
    }

    private void winFunction(){
        Toast.makeText(getApplicationContext(), "Your time for this level is " + timeElapsed + " seconds!", Toast.LENGTH_LONG).show();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        timeElapsed += mSharedPreferences.getInt("timeScore", 1000);
        mEditor.putInt("timeScore", timeElapsed).apply();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mTimer.cancel();
        Intent intent = new Intent(AtticActivity.this, WinScreenActivity.class);
        startActivity(intent);
    }

    private void advance(int stage) {
        if (stage == 6) {
            winFunction();
        } else {
            mHintView.setText(Constants.ATTIC_HINTS[stage]);
            youWin ++;
        }
    }
}
