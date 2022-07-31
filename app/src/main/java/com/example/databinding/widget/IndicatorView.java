package com.example.databinding.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.databinding.R;


public class IndicatorView extends View {

    private Paint mPaint;   // 绘制画笔

    private int cnt;
    private int position;
    private int spaceSize;

    private int activeRadius;
    private int activeColor;
    private int normalRadius;
    private int normalColor;

    public IndicatorView(Context context) {
        super(context);
        initPaint();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(attrs);
        initPaint();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(attrs);
        initPaint();
    }

    private void initAttributeSet(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        cnt = a.getInteger(R.styleable.IndicatorView_cnt, 0);
        position = a.getInteger(R.styleable.IndicatorView_position, 0);
        spaceSize = a.getDimensionPixelSize(R.styleable.IndicatorView_spaceSize, 10);
        activeRadius = a.getDimensionPixelSize(R.styleable.IndicatorView_activeRadius, 6);
        activeColor = a.getColor(R.styleable.IndicatorView_activeColor, Color.WHITE);
        normalRadius = a.getDimensionPixelSize(R.styleable.IndicatorView_normalRadius, 4);
        normalColor = a.getColor(R.styleable.IndicatorView_normalColor, Color.WHITE);
    }

    private void initPaint() {
        try {
            if (mPaint == null) {
                mPaint = new Paint();
            }
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);// 设置抗锯齿
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //如果宽高都是warp_content时，设置控件的宽高的大小
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getRealWidth(), getRealHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getRealWidth(), heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, getRealHeight());
        }
    }

    private int getRealWidth() {
        return getPaddingLeft() + getPaddingRight() + getDrawWidth();
    }

    private int getRealHeight() {
        return getPaddingTop() + getPaddingBottom() + getDrawHeight();
    }

    private int getDrawWidth() {
        if (cnt == 0) {
            return 0;
        }
        return activeRadius * 2 + normalRadius * 2 * (cnt - 1) + spaceSize * (cnt - 1);
    }

    private int getDrawHeight() {
        if (cnt == 0) {
            return 0;
        }
        return (activeRadius > normalRadius) ? activeRadius * 2 : normalRadius * 2;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        try {
            int x = getPaddingLeft();
            int y = getHeight() / 2;
            for (int i = 0; i < cnt; i++) {
                if (i == position) {
                    x += activeRadius;
                    mPaint.setColor(activeColor);
                    canvas.drawCircle(x, y, activeRadius, mPaint);
                    x += (activeRadius + spaceSize);
                } else {
                    x += normalRadius;
                    mPaint.setColor(normalColor);
                    canvas.drawCircle(x, y, normalRadius, mPaint);
                    x += (normalRadius + spaceSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
        invalidate();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public int getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(int spaceSize) {
        this.spaceSize = spaceSize;
        invalidate();
    }

}
