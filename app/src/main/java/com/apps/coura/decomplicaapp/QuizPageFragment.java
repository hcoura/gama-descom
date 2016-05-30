package com.apps.coura.decomplicaapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.QuizPage;
import com.apps.coura.decomplicaapp.model.User;
import com.apps.coura.decomplicaapp.views.ExperienceBar;
import com.apps.coura.decomplicaapp.views.MySeekBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import info.hoang8f.widget.FButton;

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
    private boolean currentAnswer = false;
    private boolean isQuizCompleted = false;
    private float mProgress;

    private AlertDialog mResultsDialog;
    private AlertDialog mNewLevelDialog;
    private AlertDialog mUnlockedModuleDialog;

    private FButton mConfirmButton;
    private RadioGroup mAnswerRadioGroup;
    private boolean hasLeveledUp = false;
    private boolean hasUnlockedModule = false;

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

        TextView questionTextView = (TextView)v.findViewById(R.id.quiz_page_text_view);
        questionTextView.setText(mQuizPage.getQuestion());

        mAnswerRadioGroup = (RadioGroup)v.findViewById(R.id.quiz_radioGroup);
        int count = mAnswerRadioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View o = mAnswerRadioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                RadioButton radioBtn =  (RadioButton)o;
                radioBtn.setText(mQuizPage.getAnswers().get(i).getAnswerText());
                final int finalI = i;
                radioBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentAnswer = mQuizPage.getAnswers().get(finalI).isCorrectAnswer();
                    }
                });
            }
        }

        mAnswerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!mConfirmButton.isEnabled()){
                    mConfirmButton.setEnabled(true);
                    mConfirmButton.setButtonColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                }
            }
        });

        mTimer = (MySeekBarCompat) v.findViewById(R.id.timer_seekBar);

        mConfirmButton = (FButton) v.findViewById(R.id.quiz_confirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAnswer) {
                    isQuizCompleted = true;
                    if (ModuleFactory.getListOfModules().get(mModPos).getQuizPages().size() <= mPos + 1) {
                        moduleCompleted();
                    } else {
                        quizCompleted();
                    }
                } else {
                    Toast.makeText(getActivity(), "Resposta Errada", Toast.LENGTH_SHORT).show();
                }
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

            mProgress = (float)mCurrentDuration / TIMER_LENGTH;

            mTimer.setProgress((int) (mProgress * 10000));
            getProgressCallback().onProgress(1 - mProgress);

            // Running this thread after 50 milliseconds
            if (mCurrentDuration <= 0 ) {
                mProgress = 0;
                Toast.makeText(getActivity(), "Tempo para pontuação bônus expirado", Toast.LENGTH_SHORT).show();
            } else if (!isQuizCompleted) {
                mHandler.postDelayed(this, HANDLER_DELAY);
            }

        }
    };

    @Subscribe
    public void onStartTimer(ModuleActivity.StartTimer event) {
        mCurrentDuration = TIMER_LENGTH;
        mHandler.postDelayed(mUpdateTimerTask, HANDLER_DELAY);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    private void quizCompleted() {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_view_results,
                (ViewGroup) getActivity().getWindow().getDecorView()
                .findViewById(android.R.id.content), false);

        TextView scoreTextView = (TextView)v.findViewById(R.id.results_dialog_score_textView);
        TextView coinsTextView = (TextView)v.findViewById(R.id.results_dialog_coins_textView);
        ExperienceBar experienceBar = (ExperienceBar)v.findViewById(R.id.results_dialog_experienceBar);
        FButton dialogConfirmButton = (FButton)v.findViewById(R.id.results_dialog_confirm_button);

        int points = (int)((float)mQuizPage.getMaxPoints()*0.5 + (mProgress) * (float)mQuizPage.getMaxPoints()*0.5);
        float expProgress;

        scoreTextView.setText(String.valueOf(points));

        User.setScore(getActivity(), mModPos, mPos, points);
        if (!User.hasCompletedQuiz(getActivity(), mModPos, mPos)) {
            User.addGoldCoins(getActivity(), mQuizPage.getGoldCoins());

            String coins = "02";
            coinsTextView.setText(coins);

            User.completedQuiz(getActivity(), mModPos, mPos);
            int exp = User.getExperience(getActivity()) + 300;
            if (exp >= 1000) {
                User.setUserLevel(getActivity(), User.getUserLevel(getActivity()) + 1);
                User.setExperience(getActivity(), exp - 1000);
                hasLeveledUp = true;
                expProgress = 1f;
            } else {
                User.setExperience(getActivity(), exp);
                expProgress = (float)exp/1000;
            }
        } else {
            expProgress = (float)User.getExperience(getActivity())/1000;
        }

        dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultsDialog != null) mResultsDialog.dismiss();
                if (hasLeveledUp) {
                    levelUpDialog();
                } else {
                    goToNextPage();
                }

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        mResultsDialog = builder.create();
        mResultsDialog.setCancelable(false);
        mResultsDialog.show();

        ObjectAnimator animator = ObjectAnimator.ofFloat(experienceBar, "progress", expProgress);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    private void levelUpDialog() {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_view_new_level,
                (ViewGroup) getActivity().getWindow().getDecorView()
                        .findViewById(android.R.id.content), false);

        TextView newLevelTitle = (TextView)v.findViewById(R.id.new_level_title_textView);
        FButton dialogConfirmButton = (FButton)v.findViewById(R.id.new_level_dialog_confirm_button);

        newLevelTitle.setText(User.getUserTitle(getActivity()));

        dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNewLevelDialog != null) mNewLevelDialog.dismiss();
                if (hasUnlockedModule){
                    unlockedModuleDialog();
                } else {
                    goToNextPage();
                }

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        mNewLevelDialog = builder.create();
        mNewLevelDialog.setCancelable(false);
        mNewLevelDialog.show();
    }

    private void goToNextPage() {
        mHandler.removeCallbacks(null);
        getNextPageCallback().onNextPage();
    }

    private void moduleCompleted() {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_view_results,
                (ViewGroup) getActivity().getWindow().getDecorView()
                        .findViewById(android.R.id.content), false);

        TextView scoreTextView = (TextView)v.findViewById(R.id.results_dialog_score_textView);
        TextView coinsTextView = (TextView)v.findViewById(R.id.results_dialog_coins_textView);
        TextView congratulateTextView = (TextView)v.findViewById(R.id.results_dialog_congratulate_textView);
        congratulateTextView.setText("Parabéns! \nMódulo concluído!");
        ExperienceBar experienceBar = (ExperienceBar)v.findViewById(R.id.results_dialog_experienceBar);
        FButton dialogConfirmButton = (FButton)v.findViewById(R.id.results_dialog_confirm_button);

        int points = (int)((float)mQuizPage.getMaxPoints()*0.5 + (mProgress) * (float)mQuizPage.getMaxPoints()*0.5);
        User.setScore(getActivity(), mModPos, mPos, points);

        int moduleScore = User.getModuleScore(getActivity(), mModPos);
        User.setScore(getActivity(), mModPos, -1, (int)(moduleScore * 0.8));

        float expProgress;

        scoreTextView.setText(String.valueOf(points + moduleScore));

        if (!User.hasCompletedQuiz(getActivity(), mModPos, mPos)) {
            User.addGoldCoins(getActivity(), mQuizPage.getGoldCoins() + 8);

            String coins = "10";
            coinsTextView.setText(coins);

            User.completedQuiz(getActivity(), mModPos, mPos);
            int exp = User.getExperience(getActivity()) + 300 + 200;
            if (exp >= 1000) {
                User.setUserLevel(getActivity(), User.getUserLevel(getActivity()) + 1);
                User.setExperience(getActivity(), exp - 1000);
                hasLeveledUp = true;
                expProgress = 1f;
            } else {
                User.setExperience(getActivity(), exp);
                expProgress = (float)exp/1000;
            }
        } else {
            expProgress = (float)User.getExperience(getActivity())/1000;
        }

        dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultsDialog != null) mResultsDialog.dismiss();
                if (hasLeveledUp) {
                    levelUpDialog();
                } else if (hasUnlockedModule && mModPos < 1 ){
                    unlockedModuleDialog();
                } else {
                    goHome();
                }

            }
        });

        User.setModuleLastPosition(getActivity(), mModPos, 0);
        if (User.getUnlockedModule(getActivity()) <= mModPos) {
            hasUnlockedModule = true;
            User.setUnlockedModule(getActivity(), mModPos + 1);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        mResultsDialog = builder.create();
        mResultsDialog.setCancelable(false);
        mResultsDialog.show();

        ObjectAnimator animator = ObjectAnimator.ofFloat(experienceBar, "progress", expProgress);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    private void goHome() {
        Intent i = new Intent(getActivity(), SubjectActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    private void unlockedModuleDialog() {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_view_unlocked_module,
                (ViewGroup) getActivity().getWindow().getDecorView()
                        .findViewById(android.R.id.content), false);

        FButton dialogConfirmButton = (FButton)v.findViewById(R.id.unlocked_dialog_confirm_button);

        dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUnlockedModuleDialog != null) mUnlockedModuleDialog.dismiss();
                goHome();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        mUnlockedModuleDialog = builder.create();
        mUnlockedModuleDialog.setCancelable(false);
        mUnlockedModuleDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimerTask);
    }
}
