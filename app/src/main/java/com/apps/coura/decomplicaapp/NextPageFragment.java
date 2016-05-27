package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class NextPageFragment extends Fragment {

    private OnNextPageListener mNextPageCallback;

    public OnNextPageListener getNextPageCallback() {
        return mNextPageCallback;
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
    }
}
