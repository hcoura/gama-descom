package com.apps.coura.decomplicaapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.apps.coura.decomplicaapp.R;


/**
 * Created by Henrique Coura on 27/05/2016.
 */
public class ExperienceBar extends View {

    private static final int BORDER_THICKNESS = 2;
    private static final int BORDER_RADIUS = 10;
    private final float mBorderThickness;

    private Paint mFillPaint;
    private Paint mBorderPaint;
    private float mProgress;
    private int mBorderColor;
    private int mFillColor;
    private float mRadius;

    public ExperienceBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExperienceBar,
                0, 0);
        //Reading values from the XML layout
        try {
            mBorderColor = typedArray.getInt(R.styleable.ExperienceBar_borderColor, ContextCompat.getColor(getContext(),R.color.colorAccent));
            mFillColor = typedArray.getInt(R.styleable.ExperienceBar_fillColor, ContextCompat.getColor(getContext(),R.color.colorPrimary));
            mProgress = typedArray.getFloat(R.styleable.ExperienceBar_experienceProgress, 0);

        } finally {
            typedArray.recycle();
        }

        mBorderThickness = dpToPx(BORDER_THICKNESS);
        mRadius = dpToPx(BORDER_RADIUS);

        mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillPaint.setColor(mFillColor);
        mFillPaint.setStyle(Paint.Style.FILL);

        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderThickness);
    }

    private int dpToPx(int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        setMeasuredDimension(width, dpToPx(20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int progressWidth = (int)((getWidth() - mBorderThickness) * mProgress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(mBorderThickness/2, mBorderThickness/2, getWidth() - mBorderThickness/2, getHeight()-mBorderThickness/2, mRadius, mRadius, mBorderPaint);
            canvas.drawRoundRect(mBorderThickness, mBorderThickness, progressWidth, getHeight()-mBorderThickness,mRadius - mBorderThickness , mRadius - mBorderThickness ,mFillPaint);
        } else {
            canvas.drawRect(mBorderThickness/2, mBorderThickness/2, getWidth() - mBorderThickness/2, getHeight()-mBorderThickness/2, mBorderPaint);
            canvas.drawRect(mBorderThickness, mBorderThickness, progressWidth, getHeight()-mBorderThickness ,mFillPaint);
        }



    }

    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }
}
