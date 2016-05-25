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
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.QuizPage;
import com.apps.coura.decomplicaapp.model.VideoPage;

import app.minimize.com.seek_bar_compat.SeekBarCompat;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class QuizPageFragment extends Fragment{

    private static final String MOD_POSITION = "mod_position";
    private static final String POSITION = "position";
    private static final int TIMER_LENGTH = 1000*60;
    private static final int HANDLER_DELAY = 100;

    private QuizPage mQuizPage;

    private Handler mHandler;
    private SeekBarCompat mTimer;
    private int mCurrentDuration = TIMER_LENGTH;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo: save instance

        int modPos = getArguments() != null ? getArguments().getInt(MOD_POSITION) : 0;
        int pos = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        mQuizPage = ModuleFactory.getListOfModules().get(modPos).getQuizPages().get(pos);

        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        mTimer = (SeekBarCompat)v.findViewById(R.id.timer_seekBar);

        mHandler.postDelayed(mUpdateTimerTask, HANDLER_DELAY);

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
//            mCurrentDuration -= HANDLER_DELAY;
//
//            float progress = (mCurrentDuration/TIMER_LENGTH) * 100;
//
//            mTimer.setProgress((int)progress);
//
//            // Running this thread after 50 milliseconds
//            if (mCurrentDuration <= 0 ){
//                Log.d("Quiz", "timer ended");
//            } else {
//                mHandler.postDelayed(this, HANDLER_DELAY);
//            }
//
//            Log.d("quiz", ""+QuizPageFragment.this);
//
//            mHandler.postDelayed(this, HANDLER_DELAY);
        }
    };

    @Override
    public void onResume() {
        Log.d("Quiz", "onResume");
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("Quiz", "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d("Quiz", "onDetach");
        super.onDetach();
    }

    @Override
    public void onStart() {

        Log.d("Quiz", "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d("Quiz", "onStop");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d("Quiz", "onPause");
        super.onPause();
    }
}
