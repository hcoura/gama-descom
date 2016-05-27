package com.apps.coura.decomplicaapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.apps.coura.decomplicaapp.R;


/**
 * Created by Henrique Coura on 27/05/2016.
 */
public class GamaProgressIndicator extends View {

    private final float PROGRESS_THICK_RATIO = 0.5f;
    private final float BASE_THICK_RATIO = 0.5f;
    private final float INNER_RADIUS_RATIO = 1f;

    private Paint mBasePaint;
    private Paint mProgressPaint;
    private int mNumOfPages;
    private int mOuterRadius;
    private int mInnerRadius;
    private float mProgress = 0f;
    private int mCurrentPage = 1;
    private int mProgressColor;
    private int mBaseColor;
    private int mBaseThickness;
    private int mProgressThickness;

    public GamaProgressIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.GamaProgressIndicator,
                0, 0);
        //Reading values from the XML layout
        try {
            mProgressColor = typedArray.getInt(R.styleable.GamaProgressIndicator_gamaProgressColor, ContextCompat.getColor(getContext(),R.color.colorAccent));
            mBaseColor = typedArray.getInt(R.styleable.GamaProgressIndicator_baseColor, ContextCompat.getColor(getContext(),R.color.colorPrimary));
            mOuterRadius = typedArray.getDimensionPixelSize(R.styleable.GamaProgressIndicator_circleRadius, dpToPx(16));
            mNumOfPages = typedArray.getInt(R.styleable.GamaProgressIndicator_numOfPages, 5);
            mProgress = typedArray.getFloat(R.styleable.GamaProgressIndicator_progress, 0);
            mCurrentPage = typedArray.getInt(R.styleable.GamaProgressIndicator_currentPage, 1);

        } finally {
            typedArray.recycle();
        }

        mProgressThickness = (int)(mOuterRadius * PROGRESS_THICK_RATIO);
        mBaseThickness = (int)(mOuterRadius * BASE_THICK_RATIO);
        mInnerRadius = (int)(mOuterRadius * INNER_RADIUS_RATIO);

        mBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBasePaint.setColor(mBaseColor);
        mBasePaint.setStyle(Paint.Style.FILL);
        mBasePaint.setStrokeWidth(mBaseThickness);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setStrokeWidth(mProgressThickness);
    }

    private int dpToPx(int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        setMeasuredDimension(width, mOuterRadius*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(mOuterRadius, getHeight()/2, getWidth() - mOuterRadius, getHeight()/2, mBasePaint);

        int cx, startX, endX;
        for (int i = 0; i < mNumOfPages; i++) {
            cx = (getWidth() - mOuterRadius*2) * (i)/(mNumOfPages-1) + mOuterRadius;
            canvas.drawCircle(cx, getHeight()/2, mOuterRadius, mBasePaint);
        }

        for (int i = 0; i < mCurrentPage; i++) {
            cx = (getWidth() - mOuterRadius*2) * (i)/(mNumOfPages-1) + mOuterRadius;
            canvas.drawCircle(cx, getHeight()/2, mInnerRadius, mProgressPaint);
        }

        for (int i = 1; i < mCurrentPage; i++) {
            startX = (getWidth() - mOuterRadius*2) * (i-1)/(mNumOfPages-1) + mOuterRadius;
            endX = (getWidth() - mOuterRadius*2) * (i)/(mNumOfPages-1) + mOuterRadius;
            canvas.drawLine(startX, getHeight()/2, endX, getHeight()/2, mProgressPaint);
        }

        if (mProgress != 0) {
            startX = (getWidth() - mOuterRadius*2) * (mCurrentPage - 1)/(mNumOfPages-1) + mOuterRadius;
            int segmentWidth = (getWidth() - mOuterRadius*2) * (mCurrentPage)/(mNumOfPages-1) + mOuterRadius - startX - mInnerRadius*2;
            int progress = (int)((float)segmentWidth*mProgress);
            endX = startX + progress + mInnerRadius;
            canvas.drawLine(startX, getHeight()/2, endX, getHeight()/2, mProgressPaint);
        }
    }

    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }

    public void setCurrentPage(int page) {
        mProgress = 0;
        mCurrentPage = page;
        invalidate();
    }

    public void setNumOfPages(int num) {
        mNumOfPages = num;
        invalidate();
    }
}
