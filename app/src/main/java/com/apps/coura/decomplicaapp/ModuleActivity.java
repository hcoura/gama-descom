package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.apps.coura.decomplicaapp.model.Module;
import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.User;
import com.apps.coura.decomplicaapp.views.GamaProgressIndicator;
import com.apps.coura.decomplicaapp.views.NoSwipeViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class ModuleActivity extends AppCompatActivity implements NextPageFragment.OnNextPageListener, NextPageFragment.OnProgressListener {

    public static final String MODULE_ID_EXTRA = "com.apps.coura.descomplicaapp.module_id";
    private Module mModule;
    private int mPos;
    private GamaProgressIndicator mIndicatorBar;
    private NoSwipeViewPager mFragmentView;
    private int mNumPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            mPos = getIntent().getIntExtra(MODULE_ID_EXTRA, -1);
            List<Module> modules = ModuleFactory.getListOfModules();
            mModule = modules.get(mPos);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mModule.getTitle());
        }

        mFragmentView = (NoSwipeViewPager) findViewById(R.id.module_fragment_view);
        mIndicatorBar = (GamaProgressIndicator) findViewById(R.id.module_progressIndicator);
        mNumPages = mModule.getVideoPages().size() + mModule.getQuizPages().size();
        mIndicatorBar.setNumOfPages(mNumPages);

        ModulePagesAdapter modulePagesAdapter = new ModulePagesAdapter(getSupportFragmentManager());

        mFragmentView.setAdapter(modulePagesAdapter);
        mFragmentView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicatorBar.setProgress(0);
                mIndicatorBar.setCurrentPage(position+1);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        goTo(User.getModuleLastPosition(this, mPos));
    }

    @Override
    public void onNextPage() {
        int nextPos = mFragmentView.getCurrentItem() + 1;
        User.setModuleLastPosition(this, mPos, nextPos);
        goTo(nextPos);
    }

    private void goTo(int nextPos) {
        mFragmentView.setCurrentItem(nextPos);
        if (nextPos % 2 != 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new StartTimer());
                }
            }, 200);
        }
    }

    @Override
    public void onProgress(float progress) {
//        Log.d("Progress", ""+progress);
        mIndicatorBar.setProgress(progress);
    }

    private class ModulePagesAdapter extends FragmentStatePagerAdapter {
        public ModulePagesAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int pos;
            if (position == 0 || position % 2 == 0) {
                pos = position == 0 ? 0 : position/2;
                return VideoPageFragment.newInstance(mPos, pos);
            } else {
                pos = (position - 1) == 0 ? 0 : (position - 1)/2;
                return QuizPageFragment.newInstance(mPos, pos);
            }
        }

        @Override
        public int getCount() {
            return mNumPages;
        }
    }

    public class StartTimer {}
}
