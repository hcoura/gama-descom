package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.ModuleFactory;
import com.apps.coura.decomplicaapp.model.VideoPage;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class VideoPageFragment extends Fragment{

    private static final String MOD_POSITION = "mod_position";
    private static final String POSITION = "position";
    private VideoPage mVideoPage;

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

        TextView videoUrl = (TextView)v.findViewById(R.id.video_page_text_view);
        videoUrl.setText(mVideoPage.getVideoUrl());

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
}
