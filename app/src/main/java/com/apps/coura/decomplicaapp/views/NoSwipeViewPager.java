package com.apps.coura.decomplicaapp.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class NoSwipeViewPager extends ViewPager {
    public NoSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSwipeViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
