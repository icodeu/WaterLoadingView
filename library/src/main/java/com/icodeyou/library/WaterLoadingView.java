package com.icodeyou.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaterLoadingView extends View {

    private Path mPath;
    private Paint mPaint;
    /**
     * 此控件的宽和高
     */
    private int mWidth, mHeight;
    /**
     * 只有一个控制点，其XY坐标
     */
    private float mControlX, mControlY;
    /**
     * 整个Wave贝塞尔曲线中两个数据点的XY坐标，mDataY与mControlY坐标增减幅一致
     */
    private float mDataX1, mDataX2, mDataY;
    /**
     * 判断控制点是该右移还是左移
     */
    private boolean mIsIncrease;
    /**
     * 水位下降速度(0~100)
     */
    private int mFallVelocity;
    /**
     * 水位左右晃动幅度(0~100)
     */
    private int mShakeVelocity;
    /**
     * wrap_content时默认宽高，单位px
     */
    private static final int VIEW_WIDTH_HEIGHT_DEFAULT = 200;

    public WaterLoadingView(Context context) {
        this(context, null);
    }

    public WaterLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPath = new Path();

        // Load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.WaterLoadingView);
        int waterColor = attributes.getColor(R.styleable.WaterLoadingView_waterColor, 0xFFA2D6AE);
        mPaint.setColor(waterColor);
        mFallVelocity = attributes.getInt(R.styleable.WaterLoadingView_waterFallVelocity, 50);
        mShakeVelocity = attributes.getInt(R.styleable.WaterLoadingView_waterShakeVelocity, 20);

        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = VIEW_WIDTH_HEIGHT_DEFAULT;
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        // 计算两个数据点的XY坐标
        mDataX1 = -1 / 4F * mWidth;
        mDataX2 = 5 / 4F * mWidth;
        mDataY = 1 / 8F * mHeight;
        // 计算控制点Y坐标
        mControlY = -1 / 16F * mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.moveTo(mDataX1, mDataY);
        mPath.quadTo(mControlX, mControlY, mDataX2, mDataY);
        mPath.lineTo(mDataX2, mHeight);
        mPath.lineTo(mDataX1, mHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        if (mControlX <= mDataX1) {
            // 当控制点的X坐标小于或等于数据点1的X坐标时更改标识值
            mIsIncrease = true;
        } else if (mControlX >= mDataX2) {
            // 当控制点的X坐标大于或等于数据点2的X坐标时更改标识值
            mIsIncrease = false;
        }

        // 根据标识值判断当前的控制点X坐标是该加还是减
        mControlX = mIsIncrease ? mControlX + mShakeVelocity : mControlX - mShakeVelocity;

        // 水位逐渐下降
        if (mControlY <= mHeight) {
            mControlY += mFallVelocity / 10.0;
            mDataY += mFallVelocity / 10.0;
        }

        mPath.reset();
        invalidate();
    }
}