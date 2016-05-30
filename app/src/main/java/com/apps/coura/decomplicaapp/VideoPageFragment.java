package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.VideoPage;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import info.hoang8f.widget.FButton;

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
    private boolean hasReleased = false;

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

        FButton skipVideoButton = (FButton) v.findViewById(R.id.skip_video_button);
        skipVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVideoCompleted = true;
                videoCompleted();
            }
        });

        TextView currentTagTextView = (TextView)v.findViewById(R.id.current_video_tag_textView);
        String currentTag = "0" + (mModPos + 1) + "." + (mPos + 1 );
        currentTagTextView.setText(currentTag);
        TextView currentTitleTextView = (TextView)v.findViewById(R.id.current_video_title_textView);
        currentTitleTextView.setText(mVideoPage.getVideoTitle());

        TextView nextTagTextView = (TextView)v.findViewById(R.id.next_video_tag_textView);
        TextView nextTitleTextView = (TextView)v.findViewById(R.id.next_video_title_textView);
        if (ModuleFactory.getListOfModules().get(mModPos).getVideoPages().size() <= mPos + 1) {
            // last video
            nextTagTextView.setText("");
            nextTitleTextView.setText("Fim do módulo!!");
        } else {
            String nextTag = "0" + (mModPos + 1) + "." + (mPos + 2 );
            nextTagTextView.setText(nextTag);
            nextTitleTextView.setText(ModuleFactory.getListOfModules().get(mModPos).getVideoPages().get(mPos + 1).getVideoTitle());
        }


        return v;
    }

    private void videoCompleted() {
        mHandler.removeCallbacks(mUpdateProgressTask);
        if (mYouTubePlayer != null) {
            mYouTubePlayer.release();
            hasReleased = true;
        }
        getNextPageCallback().onNextPage();
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
            if (mYouTubePlayer != null && !hasReleased) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateProgressTask);
    }
}
