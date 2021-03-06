package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.Module;
import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.User;
import com.apps.coura.decomplicaapp.views.ExperienceBar;

import java.util.List;

public class SubjectActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("História");
        }

        TextView scoreTextView = (TextView)findViewById(R.id.score_textView);
        if (scoreTextView != null) {
            String score = "" + User.getScore(this);
            scoreTextView.setText(score);
        }

        TextView coinsTextView = (TextView)findViewById(R.id.coins_textView);
        if (coinsTextView != null) {
            String coins = "" + (User.getGoldCoins(this) - User.getSpentCoins(this));
            coinsTextView.setText(coins);
        }

        ExperienceBar expBar = (ExperienceBar)findViewById(R.id.experience_bar);
        if (expBar != null) {
            expBar.setProgress((float)User.getExperience(this)/1000);
        }

        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        if (titleTextView != null) {
            titleTextView.setText(User.getUserTitle(this));
        }

        ImageView profileImageView = (ImageView)findViewById(R.id.profile_image);
        if (profileImageView != null) {
            profileImageView.setImageResource(User.mAvatarIds[User.getAvatarId(this)]);
        }

        setRecyclerView();
    }

    private void setRecyclerView() {
        List<Module> modules = ModuleFactory.getListOfModules();

        mRecyclerView = (RecyclerView) findViewById(R.id.module_recycler);

        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ModuleAdapter(modules, this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
