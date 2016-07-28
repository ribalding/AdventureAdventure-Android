package com.epicodus.imagesearch.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.epicodus.imagesearch.ui.KitchenActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.epicodus.imagesearch.Constants;
import com.epicodus.imagesearch.R;
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
    @Bind(R.id.arc_progress) ArcProgress mArc_Progress;


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
            mCucumber.setOnClickListener(this);
            mArc_Progress.setProgress(43);
            advance(youWin);
        }
        if (view == mCucumber) {
            mHeartButton.setOnClickListener(this);
            mArc_Progress.setProgress(47);
            advance(youWin);
        }
        if (view == mHeartButton) {
            mCrownButton.setOnClickListener(this);
            mArc_Progress.setProgress(50);
            advance(youWin);
        }
        if (view == mCrownButton) {
            mCatsGameButton.setOnClickListener(this);
            mArc_Progress.setProgress(53);
            advance(youWin);
        }
        if (view == mCatsGameButton) {
            mSquirrelButton.setOnClickListener(this);
            mArc_Progress.setProgress(57);
            advance(youWin);
        }
        if (view == mSquirrelButton) {
            mArc_Progress.setProgress(60);
            advance(youWin);
        }

        view.setOnClickListener(null);
    }

    private void winFunction(){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        timeElapsed += mSharedPreferences.getInt("timeScore", 1000);
        mEditor.putInt("timeScore", timeElapsed).apply();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
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
