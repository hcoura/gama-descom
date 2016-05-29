package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class NextPageFragment extends Fragment {

    private OnNextPageListener mNextPageCallback;
    private OnProgressListener mProgressCallback;

    public OnNextPageListener getNextPageCallback() {
        return mNextPageCallback;
    }

    public OnProgressListener getProgressCallback() {
        return mProgressCallback;
    }

    public interface OnNextPageListener {
        void onNextPage();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mNextPageCallback = (OnNextPageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNextPageListener");
        }

        try {
            mProgressCallback = (OnProgressListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnProgressListener");
        }
    }

    public interface OnProgressListener {
        void onProgress(float progress);
    }

}
