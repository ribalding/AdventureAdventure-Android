package com.epicodus.imagesearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.imagesearch.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrophyRoomActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.eyeButton) Button mEyeButton;
    @Bind(R.id.hatButton) Button mHatButton;
    @Bind(R.id.handprintButton) Button mHandprintButton;
    private Integer youWin;
    private Integer winNumber;

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
    }
}
