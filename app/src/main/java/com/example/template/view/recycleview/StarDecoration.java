package com.example.template.view.recycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.adapter.StarAdapter;

/**
 * 功能描述：
 * Created by gfq on 2020/10/16
 **/
public class StarDecoration extends RecyclerView.ItemDecoration {

    private int groupHeaderHeight;

    private Paint mHeadBgPaint;
    private Paint mHeadTextPaint;
    private Rect mTextRect;

    public StarDecoration(Context context) {

        groupHeaderHeight = dp2px(context, 100);

        mHeadBgPaint = new Paint();
        mHeadBgPaint.setColor(Color.RED);

        mHeadTextPaint = new Paint();
        mHeadTextPaint.setColor(Color.WHITE);
        mHeadTextPaint.setTextSize(50);

        mTextRect = new Rect();

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            //屏幕显示的item个数
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                //获取对应i的View
                View view = parent.getChildAt(i);
                //获取View 的布局位置
                int position = parent.getChildLayoutPosition(view);
                //是否是头部
                boolean isGroupHeader = adapter.isGroupHeader(position);
                //优化也会绘制padding区域的bug
                boolean canDraw = view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0;
                if (canDraw) {
                    if (isGroupHeader) {
                        c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), mHeadBgPaint);
                        String groupName = adapter.getGroupName(position);
                        mHeadTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                        c.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2 + mTextRect.height() / 2, mHeadTextPaint);
                    } else {
                        //分割线
                        c.drawRect(left, view.getTop() - 1, right, view.getTop(), mHeadBgPaint);
                    }
                }


            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();

            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            //返回可见区域内的第一个item的position
            int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            //获取对应position的view
//            View view = parent.getChildAt(position);//滑动的时候，view 会有null的情况
            View view = parent.findViewHolderForAdapterPosition(position).itemView;

            //当第二个是组的头部的时候
            boolean isGroupHeader = adapter.isGroupHeader(position + 1);
            if (isGroupHeader) {

                int bottom = Math.min(groupHeaderHeight, view.getBottom() - parent.getPaddingTop());

                c.drawRect(left, top, right, top + bottom, mHeadBgPaint);
                String groupName = adapter.getGroupName(position);
                mHeadTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                //绘制的文字的高度不能超出区域
                c.clipRect(left, top, right, top + bottom);
                c.drawText(groupName, left + 20, top + bottom - groupHeaderHeight / 2 + mTextRect.height() / 2, mHeadTextPaint);
            } else {
                c.drawRect(left, top, right, top + groupHeaderHeight, mHeadBgPaint);
                String groupName = adapter.getGroupName(position);
                mHeadTextPaint.getTextBounds(groupName, 0, groupName.length(), mTextRect);
                c.drawText(groupName, left + 20, top + groupHeaderHeight / 2 + mTextRect.height() / 2, mHeadTextPaint);
            }


        }


    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getAdapter() instanceof StarAdapter) {

            //怎么判断 itemView是头部
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int position = parent.getChildLayoutPosition(view);
            boolean isGroupHeader = adapter.isGroupHeader(position);
            if (isGroupHeader) {
                //如果是头部，预留更大的空间
                outRect.set(0, groupHeaderHeight, 0, 0);
            } else {
                outRect.set(0, 1, 0, 0);
            }
        }

    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5);
    }
}
