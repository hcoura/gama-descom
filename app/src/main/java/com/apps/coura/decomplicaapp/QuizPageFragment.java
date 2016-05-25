package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.QuizPage;
import com.apps.coura.decomplicaapp.model.VideoPage;
import com.apps.coura.decomplicaapp.views.MySeekBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.minimize.com.seek_bar_compat.SeekBarCompat;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class QuizPageFragment extends NextPageFragment {

    private static final String MOD_POSITION = "mod_position";
    private static final String POSITION = "position";
    private static final int TIMER_LENGTH = 1000 * 60;
    private static final int HANDLER_DELAY = 100;

    private QuizPage mQuizPage;

    private Handler mHandler;
    private MySeekBarCompat mTimer;
    private int mPos;
    private int mModPos;
    private int mCurrentDuration = TIMER_LENGTH;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo: save instance


        mModPos = getArguments() != null ? getArguments().getInt(MOD_POSITION) : 0;
        mPos = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        mQuizPage = ModuleFactory.getListOfModules().get(mModPos).getQuizPages().get(mPos);

        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        mTimer = (MySeekBarCompat) v.findViewById(R.id.timer_seekBar);

        mHandler.postDelayed(mUpdateTimerTask, HANDLER_DELAY);

        Button confirmButton = (Button) v.findViewById(R.id.quiz_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextPageCallback().onNextPage();
            }
        });

        return v;
    }

    public static QuizPageFragment newInstance(int mod_position, int position) {

        Bundle args = new Bundle();
        args.putInt(MOD_POSITION, mod_position);
        args.putInt(POSITION, position);
        QuizPageFragment fragment = new QuizPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Runnable mUpdateTimerTask = new Runnable() {
        public void run() {
            mCurrentDuration -= HANDLER_DELAY;

            float progress = ((float)mCurrentDuration / TIMER_LENGTH) * 100;

            mTimer.setProgress((int) progress);

            // Running this thread after 50 milliseconds
            if (mCurrentDuration <= 0) {
                Log.d("Quiz", "timer ended");
            } else {
                mHandler.postDelayed(this, HANDLER_DELAY);
            }

        }
    };

    @Subscribe
    public void onStartTimer(ModuleActivity.StartTimer event) {
        mCurrentDuration = HANDLER_DELAY;
        mHandler.postDelayed(mUpdateTimerTask, HANDLER_DELAY);
    }

    @Override
    public void onPause() {
        Log.d("Quiz", "onPause");
        super.onPause();
    }
}
