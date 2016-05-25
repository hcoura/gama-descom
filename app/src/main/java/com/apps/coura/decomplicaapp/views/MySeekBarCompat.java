package com.apps.coura.decomplicaapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import app.minimize.com.seek_bar_compat.SeekBarCompat;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class MySeekBarCompat extends SeekBarCompat {

    public MySeekBarCompat(Context context) {
        super(context);
    }

    public MySeekBarCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
