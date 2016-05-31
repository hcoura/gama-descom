package com.apps.coura.decomplicaapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.User;

public class ProfileActivity extends AppCompatActivity {

    TextView nameUser;
    ImageView imageUser;
    TextView scoreUser;
    TextView coinsUser;
    TextView titleUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        nameUser = (TextView) findViewById(R.id.profile_username_textView);
        nameUser.setText(User.getUserName(this));

        titleUser = (TextView) findViewById(R.id.profile_title_textView);
        titleUser.setText(User.getUserTitle(this));

        imageUser = (ImageView) findViewById(R.id.profile_avatar_image);
        imageUser.setImageResource(User.mAvatarIds[User.getAvatarId(this)]);

        String score = ""+User.getScore(this);
        scoreUser = (TextView) findViewById(R.id.profile_score_textView);
        if (scoreUser != null) {
            scoreUser.setText(score);
        }

        String coins = "" + (User.getGoldCoins(this) - User.getSpentCoins(this));

        coinsUser = (TextView) findViewById(R.id.profile_coins_textView);
        if (coinsUser != null) {
            coinsUser.setText(coins);
        }
    }
}