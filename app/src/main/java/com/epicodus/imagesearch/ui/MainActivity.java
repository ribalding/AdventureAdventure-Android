package com.epicodus.imagesearch.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.imagesearch.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.startButton) Button mStartButton;
    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;
    @Bind(R.id.rulesButton) Button mRulesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mStartButton.setOnClickListener(this);
        mRulesButton.setOnClickListener(this);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mWelcomeTextView.setTypeface(pacificoFont);
    }

    @Override
    public void onClick(View view) {
        if (view == mStartButton) {
            Intent intent = new Intent(MainActivity.this, TrophyRoomActivity.class);
            startActivity(intent);
        }
        if (view == mRulesButton) {
            Intent intent = new Intent(MainActivity.this, RulesActivity.class);
            startActivity(intent);
        }
    }
}
