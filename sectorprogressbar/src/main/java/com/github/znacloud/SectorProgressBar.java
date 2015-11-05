package com.github.znacloud;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.github.znacloud.sectorprogressbar.R;

/**
 * Created by Administrator on 2015/10/28.
 */
public class SectorProgressBar extends View {


    private final static int BORDER_COLOR = Color.parseColor("#3F51B5");
    private final static int SECTOR_COLOR = Color.parseColor("#A03F51B5");
    private final static int BORDER_WIDTH = 2;
    private static final int TEXT_COLOR = Color.DKGRAY;


    private int mWidth = 0;
    private int mHeight = 0;

    private int mBorderColor = BORDER_COLOR;
    private float mBorderWidth = BORDER_WIDTH;
    private int mSectorColor = SECTOR_COLOR;
    private int mSectorBackgroundColor = Color.TRANSPARENT;
    private boolean mShowTextPercent = true;
    private Paint mPaint = new Paint();
    private RectF mOvalRect = new RectF();
    private int mProgress = 0;


    private float mRadius;
    private float mScale;

    public SectorProgressBar(Context context) {
        super(context);
        init(context);
    }


    public SectorProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SectorProgressBar, defStyleAttr, 0);
        mBorderColor = a.getColor(R.styleable.SectorProgressBar_borderColor, BORDER_COLOR);
        mBorderWidth = a.getDimension(R.styleable.SectorProgressBar_borderWidth, BORDER_WIDTH);
        mSectorColor = a.getColor(R.styleable.SectorProgressBar_sectorColor, SECTOR_COLOR);
        mSectorBackgroundColor = a.getColor(R.styleable.SectorProgressBar_sectorBackgroundColor, Color.TRANSPARENT);
        mShowTextPercent = a.getBoolean(R.styleable.SectorProgressBar_showTextProgress, true);
        mProgress = a.getInt(R.styleable.SectorProgressBar_progress, 0);
        a.recycle();
    }


    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mOvalRect = new RectF();
        mScale = context.getResources().getDisplayMetrics().density;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        mRadius = Math.min(mWidth, mHeight) / 2f;
        if (mRadius < 0) mRadius = 0;

        mOvalRect.set(0, 0, mWidth - mBorderWidth * 2, mHeight - mBorderWidth * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆框
        //填充
        if (Color.alpha(mSectorBackgroundColor) != Color.TRANSPARENT) {
            mPaint.setColor(mSectorBackgroundColor);
            canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius - mBorderWidth, mPaint);
        }
        //边框
        if (mBorderWidth > 0) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mBorderColor);
            mPaint.setStrokeWidth(mBorderWidth);
            canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius - mBorderWidth / 2f, mPaint);
        }
        //绘制扇形
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mSectorColor);
        canvas.save();
        canvas.translate(mBorderWidth, mBorderWidth);
        float angle = 360 * mProgress / 100f;
        canvas.drawArc(mOvalRect, -90, angle, true, mPaint);
        canvas.restore();

        //绘制文字
        if (mShowTextPercent) {
            String text = mProgress + "%";
            mPaint.setTextSize(mScale * 12);
            mPaint.setColor(TEXT_COLOR);
            float width = mPaint.measureText(text);

            canvas.drawText(text, (mWidth - width) / 2f, (mHeight + mScale * 12) / 2f, mPaint);
        }
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public float getBorderWidth() {
        return mBorderWidth;
    }

    public int getProgress() {
        return mProgress;
    }

    public int getSectorBackgroundColor() {
        return mSectorBackgroundColor;
    }

    public int getSectorColor() {
        return mSectorColor;
    }

    public boolean isShowTextPercent() {
        return mShowTextPercent;
    }

    public void setBorderColor(int mBorderColor) {
        this.mBorderColor = mBorderColor;
        invalidate();
    }

    public void setBorderWidth(float mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
        invalidate();
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public void setSectorBackgroundColor(int mSectorBackgroundColor) {
        this.mSectorBackgroundColor = mSectorBackgroundColor;
        invalidate();
    }

    public void setSectorColor(int mSectorColor) {
        this.mSectorColor = mSectorColor;
        invalidate();
    }

    public void setShowTextPercent(boolean mShowTextPercent) {
        this.mShowTextPercent = mShowTextPercent;
        invalidate();
    }
}
