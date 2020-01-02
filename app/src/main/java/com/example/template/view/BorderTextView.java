package com.example.template.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.common.util.L;
import com.example.template.R;

/**
 * 功能描述：自定义TextView ，可以设置背景色（正常状态 和 点击状态，不能被点击状态），还可以添加边框
 * <p>
 * 可以学习使用：
 * Drawable
 * DrawableContainer
 * StateListDrawable
 * <p>
 * Created by gfq on 2020/1/2.
 */
public class BorderTextView extends AppCompatTextView {

    private int contentColor;   // 背景颜色
    private int cornerRadius;   // 圆角半径

    private int strokeWidth;    // 边框线宽
    private int strokeColor;    // 边框颜色

    private int pressedColor;   // 按下背景颜色
    private int disableColor;    // 不可点击颜色

    private boolean mFollowTextColor; // 边框颜色是否跟随文字颜色

    private Paint mPaint = new Paint();     // 画边框所使用画笔对象
    private RectF mRectF = new RectF();     // 画边框要使用的矩形

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BorderTextView);
        contentColor = ta.getColor(R.styleable.BorderTextView_contentBackColor, Color.TRANSPARENT);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.BorderTextView_cornerRadius, 0);

        strokeColor = ta.getColor(R.styleable.BorderTextView_strokeColor, contentColor);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.BorderTextView_strokeWidth, 0);

        pressedColor = ta.getColor(R.styleable.BorderTextView_contentPressedColor, contentColor);
        disableColor = ta.getColor(R.styleable.BorderTextView_disableBackColor, Color.parseColor("#999999"));

        mFollowTextColor = ta.getBoolean(R.styleable.BorderTextView_followTextColor, false);

        ta.recycle();

        initView();

    }

    private void initView() {
        // 初始化画笔
        mPaint.setStyle(Paint.Style.STROKE);     // 空心效果
        mPaint.setAntiAlias(true);               // 设置画笔为无锯齿
        mPaint.setStrokeWidth(strokeWidth);      // 线宽

        /**
         * 设置边框线的颜色
         * 如果声明为边框跟随文字颜色 且 当前边框颜色与文字颜色不同时重新设置边框颜色
         */
        if (mFollowTextColor && strokeColor != getCurrentTextColor()) {
            strokeColor = getCurrentTextColor();
        }

        mPaint.setColor(strokeColor);            // 设置画笔颜色

        // 设置背景
        setBackgroundDrawable(getPressedSelector(contentColor, pressedColor, disableColor, cornerRadius));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画空心圆角矩形
        if (strokeWidth > 0) {
            mRectF.left = mRectF.top = 0.5f * strokeWidth;
            mRectF.right = getMeasuredWidth() - 0.5f * strokeWidth;
            mRectF.bottom = getMeasuredHeight() - 0.5f * strokeWidth;
            canvas.drawRoundRect(mRectF, cornerRadius, cornerRadius, mPaint);
        }
    }

    /**
     * 用java代码的方式动态生成状态选择器
     *
     * @param normalColor   正常背景颜色
     * @param pressedColor  按下时颜色
     * @param disabledColor 不可点击背景颜色
     * @param radius
     * @return
     */
    private Drawable getPressedSelector(int normalColor, int pressedColor, int disabledColor, int radius) {
        Drawable normal = createShape(normalColor, radius);
        Drawable pressed = createShape(pressedColor, radius);
        Drawable disabled = createShape(disabledColor, radius);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);    // 按下状态 , 设置按下的图片
        drawable.addState(new int[]{android.R.attr.state_enabled}, normal);     // 默认状态,默认状态下的图片
        drawable.addState(new int[]{}, disabled);                                // 不可点击状态
        //设置状态选择器过度动画/渐变选择器/渐变动画
//        drawable.setEnterFadeDuration(500);
//        drawable.setExitFadeDuration(500);
        return drawable;
    }

    /**
     * 创建drawable对象
     *
     * @param color
     * @param radius
     * @return
     */
    private GradientDrawable createShape(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);//设置4个角的弧度
        drawable.setColor(color);// 设置颜色
        return drawable;
    }

    /**
     * 修改边框宽度
     *
     * @param borderWidth 边框宽度 px
     */
    public void setStrokeWidth(int borderWidth) {
        try {
            strokeWidth = borderWidth;
            invalidate();
        } catch (Exception e) {
            L.e("My_Error", e.toString());
        }
    }

    /**
     * 修改边框颜色
     *
     * @param colorResource 边框颜色值 R.color.XXXX
     */
    public void setStrokeColor(int colorResource) {
        try {
            strokeColor = ContextCompat.getColor(getContext(), colorResource);
            invalidate();
        } catch (Exception e) {
            L.e("My_Error", e.toString());
        }
    }

    /**
     * 修改背景颜色
     *
     * @param colorResource 背景颜色值 R.color.XXXX
     */
    public void setContentColorResource(int colorResource) {
        setContentColorResource(colorResource, colorResource);
    }

    /**
     * 修改背景颜色
     *
     * @param normalColorResource 正常背景颜色 R.color.XXXX
     * @param pressColorResource  按下背景颜色 R.color.XXXX
     */
    public void setContentColorResource(int normalColorResource, int pressColorResource) {
        try {
            contentColor = ContextCompat.getColor(getContext(), normalColorResource);
            pressedColor = ContextCompat.getColor(getContext(), pressColorResource);
            setBackgroundDrawable(getPressedSelector(contentColor, pressedColor, disableColor, cornerRadius));
        } catch (Exception e) {
            L.e("My_Error", e.toString());
        }
    }
}
