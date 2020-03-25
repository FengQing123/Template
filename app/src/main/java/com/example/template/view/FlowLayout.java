package com.example.template.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：自定义FlowLayout，实现流布局
 * Created by gfq on 2020/3/25.
 */
public class FlowLayout extends ViewGroup {

    private int mHorizontalSpace = 10;
    private int mVerticalSpace = 10;

    private List<List<View>> allLines;//记录所有的行，一行一行存储，用于layout
    private List<Integer> lineHeights;//记录每一行的行高，用户layout

    //Java代码new的时候调用
    public FlowLayout(Context context) {
        this(context, null);
    }

    //xml文件设置时调用
    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //有设置主题style的时候调用
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        allLines = new ArrayList<>();
        lineHeights = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //度量孩子

        init();

        /**
         * 父布局的padding
         */
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        /**
         * 父布局的宽高属性
         */
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int parentWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int parentHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int parentNeedWidth = 0;
        int parentNeedHeight = 0;

        List<View> lineViews = new ArrayList<>();//保存一行中所有的View
        int lineWidthUsed = 0;//记录一行使用的大小
        int lineHeightUsed = 0;//记录一行的行高

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, layoutParams.height);

            //度量孩子
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);


            //子view实际宽度 + 右空格
            int childRealWidth = childView.getMeasuredWidth() + mHorizontalSpace;
            //子view实际高度 + 下空格
            int childRealHeight = childView.getMeasuredHeight() + mVerticalSpace;

            //判断一行
            if (lineWidthUsed + childRealWidth + paddingLeft + paddingRight > parentWidth) {

                allLines.add(lineViews);
                lineHeights.add(lineHeightUsed);

                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed);
                parentNeedHeight += lineHeightUsed;

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeightUsed = 0;

            }

            lineViews.add(childView);//记录一行的view,方便layout的布局
            lineWidthUsed += childRealWidth;
            lineHeightUsed = Math.max(lineHeightUsed, childRealHeight);

            //最后一行时
            if (i == childCount - 1) {
                int lastLineHeight = lineHeightUsed - mVerticalSpace;//添加最后一行时，需要把m VerticalSpace下空格 去掉
                allLines.add(lineViews);
                lineHeights.add(lastLineHeight);
                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed);
                parentNeedHeight += lastLineHeight;
            }
        }


        /**
         * 根据子view的度量结果，重新度量自己的ViewGroup
         * 但是自己也需要根据自己的父亲给的大小来度量
         * MeasureSpec.EXACTLY:自己的父亲已经限制了自己的大小，就算子view需要更大的空间，也只能使用父亲给自己的大小
         * MeasureSpec.AT_MOST:可以随便多大，如果超过父亲给的大小，在onLayout中会被截断
         */
        int realWidth = (parentWidthMode == MeasureSpec.EXACTLY) ? parentWidth : (parentNeedWidth + paddingLeft + paddingRight);
        int realHeight = (parentHeightMode == MeasureSpec.EXACTLY) ? parentHeight : (parentNeedHeight + paddingTop + paddingBottom);

        //度量自己
        setMeasuredDimension(realWidth, realHeight);
    }

    /**
     * onLayout方法中，其实是对已经测量好大小的子view进行摆放
     * 摆放时的坐标点也是要考虑对应的空格mHorizontal和mVerticalSpace
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = allLines.size();
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = allLines.get(i);
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);

                curL = right + mHorizontalSpace;
            }

            curL = getPaddingLeft();
            curT = curT + lineHeight;
        }
    }
}
