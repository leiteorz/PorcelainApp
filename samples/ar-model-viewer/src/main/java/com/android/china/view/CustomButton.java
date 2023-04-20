package com.android.china.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.ar.sceneform.samples.gltf.R;

/**
 * @Author Crwei
 * date 2023/4/20 19:13
 */

public class CustomButton extends AppCompatTextView {
//    自定义View ar的那个按钮 测试
    //    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试
//    自定义View ar的那个按钮 测试

    private boolean reflectLight = false;
    private int borderWidth = 0;
    private int borderColor = Color.WHITE;
    private int radius = 0;
    private int bgColor = Color.WHITE;

    private Paint mPaint;


    public CustomButton(@NonNull Context context) {
        this(context, null);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray attrArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton);
            reflectLight = attrArray.getBoolean(R.styleable.CustomButton_reflectLight, reflectLight);
            borderWidth = attrArray.getDimensionPixelSize(R.styleable.CustomButton_borderWidth, borderWidth);
            borderColor = attrArray.getColor(R.styleable.CustomButton_borderColor, borderColor);
            radius = attrArray.getDimensionPixelSize(R.styleable.CustomButton_radius, radius);
            bgColor = attrArray.getColor(R.styleable.CustomButton_bgColor, bgColor);
            attrArray.recycle();

            // 背景
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(bgColor);
            gd.setCornerRadius(radius);
            if (borderWidth > 0) {
                gd.setStroke(borderWidth, borderColor);
            }
            this.setBackground(gd);
        }
    }

    public void setBackgroundColor(@ColorInt int color) {
        GradientDrawable gd = (GradientDrawable) getBackground();
        gd.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (reflectLight) {
            int r = getMeasuredHeight() / 12;
            int l = getMeasuredWidth() / 5;
            if (r > 0 && l > 0) {
                mPaint = getPaint();
                mPaint.setColor(Color.WHITE);
                mPaint.setAntiAlias(true);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(2 * r, 2 * r,r,mPaint);
                canvas.drawRoundRect(new RectF(4 * r, r, l + 4 * r, 3 * r), r, r, mPaint);
            }
        }
    }
}