package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.VideoPage;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class VideoPageFragment extends Fragment{

    private static final String MOD_POSITION = "mod_position";
    private static final String POSITION = "position";
    private VideoPage mVideoPage;
    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayerSupportFragment youTubePlayerFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo: save instance

        int modPos = getArguments() != null ? getArguments().getInt(MOD_POSITION) : 0;
        int pos = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        mVideoPage = ModuleFactory.getListOfModules().get(modPos).getVideoPages().get(pos);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_player);
        youTubePlayerFragment.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("sDC3OYLQCyo");
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

                    }

                    @Override
                    public void onVideoEnded() {
                        Log.d("Video", "Video has ended");
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


        return v;
    }

    public static VideoPageFragment newInstance(int mod_position, int position) {

        Bundle args = new Bundle();
        args.putInt(MOD_POSITION, mod_position);
        args.putInt(POSITION, position);
        VideoPageFragment fragment = new VideoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


//    mYouTubePlayerView = (YouTubePlayerView)v.findViewById(R.id.youtube_player);
//    mYouTubePlayerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
//        @Override
//        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//            youTubePlayer.loadVideo("sDC3OYLQCyo");
//        }
//
//        @Override
//        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//        }
//    });
}
