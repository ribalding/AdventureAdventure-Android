package com.epicodus.imagesearch.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.imagesearch.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WinScreenActivity extends AppCompatActivity {

    @Bind(R.id.winImageView) ImageView mWinView;
    @Bind(R.id.winTimeView) TextView mWinTimeView;

    SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
        ButterKnife.bind(this);

        final String finalTime = Integer.toString(mPreferences.getInt("timeScore", 1000));
        final String scoreDisplay = "Your final time was " + finalTime + " seconds!";

        Picasso.with(getApplicationContext())
                .load(R.drawable.freedom)
                .into(mWinView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mWinTimeView.setText(scoreDisplay);
                    }

                    @Override
                    public void onError() {
                        Log.d("bitch ", "DAB");
                    }
                });


    }


}
