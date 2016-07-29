package com.epicodus.imagesearch.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.imagesearch.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.rulesTitleView) TextView mRulesTitleView;
    @Bind(R.id.rulesView) TextView mRulesView;
    @Bind(R.id.backButton) Button mBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ButterKnife.bind(this);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mRulesTitleView.setTypeface(pacificoFont);

        mBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBackButton) {
            finish();
        }
    }
}
