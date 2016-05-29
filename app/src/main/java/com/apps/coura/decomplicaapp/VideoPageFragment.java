package com.apps.coura.decomplicaapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.VideoPage;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class VideoPageFragment extends NextPageFragment {

    private static final String MOD_POSITION = "mod_position";
    private static final String POSITION = "position";
    private static final long HANDLER_DELAY = 1000;
    private VideoPage mVideoPage;
    private int mModPos;
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private int mPos;

    private Handler mHandler;

    private YouTubePlayer mYouTubePlayer;
    private boolean isVideoCompleted = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo: save instance

        mHandler = new Handler();

        mModPos = getArguments() != null ? getArguments().getInt(MOD_POSITION) : 0;
        mPos = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        mVideoPage = ModuleFactory.getListOfModules().get(mModPos).getVideoPages().get(mPos);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_player);
        youTubePlayerFragment.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(mVideoPage.getVideoUrl());
                mYouTubePlayer = youTubePlayer;
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String s) {
                    }

                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onVideoStarted() {
                        mHandler.postDelayed(mUpdateProgressTask, HANDLER_DELAY);
                    }

                    @Override
                    public void onVideoEnded() {
                        isVideoCompleted = true;
                        videoCompleted();
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        Button nexPage = (Button) v.findViewById(R.id.change_page_button);
        nexPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVideoCompleted = true;
                videoCompleted();
            }
        });


        return v;
    }

    private void videoCompleted() {
        mHandler.removeCallbacks(mUpdateProgressTask);
        mYouTubePlayer.release();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Você acabou de assistir o vídeo e ganhar 50 pts!")
                .setTitle("Parabéns!")
                .setPositiveButton("Ir para o teste", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        getNextPageCallback().onNextPage();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public static VideoPageFragment newInstance(int mod_position, int position) {

        Bundle args = new Bundle();
        args.putInt(MOD_POSITION, mod_position);
        args.putInt(POSITION, position);
        VideoPageFragment fragment = new VideoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Runnable mUpdateProgressTask = new Runnable() {
        public void run() {
            float progress = 0;
            if (mYouTubePlayer != null) {
                progress = (float) mYouTubePlayer.getCurrentTimeMillis() / mYouTubePlayer.getDurationMillis();
            }


            if (progress != 0) {
                getProgressCallback().onProgress(progress);
            }

            if (!isVideoCompleted) {
                mHandler.postDelayed(this, HANDLER_DELAY);
            }
        }
    };
}
