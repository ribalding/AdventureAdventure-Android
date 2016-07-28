package com.epicodus.imagesearch.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.epicodus.imagesearch.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WinScreenActivity extends AppCompatActivity {
    String imageUri;
    String imageTestUrl;
    @Bind(R.id.winGifView) ImageView mWinGifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
        ButterKnife.bind(this);

        imageUri = "file:///android_asset/youre_awesome.gif";

        Glide.with(getApplicationContext()).load(R.drawable.youre_awesome).into(mWinGifView);
    }
}
