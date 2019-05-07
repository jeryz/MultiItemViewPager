package com.zjr.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zjr on 2016/6/18.
 */
public class MultiItemViewPager extends ViewPager {

    private Paint mPaint;
    private int widthSize;
    private float mPageOffset;
    private int paddind;
    private PageTransformer mTransformer;
    private int centerX;
    private int itemWidth;
    private int mPageCount = 3;
    private int mBorderColor = Color.WHITE;
    private boolean mBorderVisible;
    private boolean mAlignLeft;

    public MultiItemViewPager(Context context) {
        this(context, null);
    }

    public MultiItemViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setClipChildren(false);
        setClipToPadding(false);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mTransformer != null) {
                    final int childCount = getChildCount();
                    int width = getWidth() - getPaddingLeft() - getPaddingRight();
                    for (int i = 0; i < childCount; i++) {
                        final View child = getChildAt(i);
                        final float transformPos = (float) (child.getLeft() - getScrollX() - paddind) / width;
                        mTransformer.transformPage(child, transformPos);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setPageOffset(float pageOffset) {
        //[0,1]
        mPageOffset = pageOffset;
    }

    public void setPageTransformer(ViewPager.PageTransformer transformer) {
        mTransformer = transformer;
    }

    public void setVisiblePageCount(int pageCount) {
        mPageCount = pageCount;
        setOffscreenPageLimit(pageCount+1);
    }

    public void setBorderVisible(boolean visible){
        mBorderVisible = visible;
    }

    public void setAlignLeft(boolean alignLeft){
        mAlignLeft = alignLeft;
    }

    public void setBorderColor(int color) {
        mBorderColor = color;
    }

    @Override
    public void setPageMargin(int marginPixels) {

        int margin = (int) (getResources().getDisplayMetrics().density * marginPixels);
        super.setPageMargin(margin);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthSize = resolveSize(0, widthMeasureSpec);
        int pageWidth = widthSize / mPageCount;
        itemWidth = pageWidth + (int) (pageWidth * mPageOffset);
        paddind = (widthSize - itemWidth) / 2;
        if(!mAlignLeft){
            setPadding(paddind, 0, paddind, 0);
        }else{
            setPadding(0, 0, paddind*2, 0);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getChildCenterX(View view) {
        //setClipToPadding(true) , canvas.clipRect(mScrollX + mPaddingLeft, t,r,b);
        return view.getLeft() - getScrollX() + (view.getWidth() / 2);
    }

    private int getCenterX() {
        return getWidth() / 2;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if(!mBorderVisible)return super.drawChild(canvas, child, drawingTime);

        int save = canvas.save();
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        drawSelectBorder(canvas, child);
        canvas.restoreToCount(save);
        return drawChild;
    }

    private void drawSelectBorder(Canvas canvas, View child) {
        centerX = getCenterX();
        if (Math.abs(centerX - getChildCenterX(child)) < 1) {
            mPaint.setColor(mBorderColor);
            mPaint.setStrokeWidth(2.0f);
            canvas.drawRect(new RectF((float) child.getLeft(), (float) child.getTop(), (float) child.getRight(), (float) child.getBottom()), mPaint);
        }
    }

}
