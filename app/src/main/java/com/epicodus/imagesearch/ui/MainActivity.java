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
    @Bind(R.id.welcomeImageView) ImageView mWelcomeImageView;
    @Bind(R.id.startButton) Button mStartButton;
    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.welcome_image).fit().into(mWelcomeImageView);
        mStartButton.setOnClickListener(this);
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mWelcomeTextView.setTypeface(pacificoFont);
    }

    @Override
    public void onClick(View view) {
        if (view == mStartButton) {
            Intent intent = new Intent(MainActivity.this, EgyptActivity.class);
            startActivity(intent);
        }
    }
}
