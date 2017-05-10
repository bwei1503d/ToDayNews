package com.example.bwei.todaynews.other;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bwei.todaynews.R;

/**
 * Created by muhanxi on 17/5/3.
 */

public class CustomTextView extends TextView {

    //  显示的内容
    private String mText;

//    文本的颜色
    private int mTextColor;

//    字体大小
    private int mTextSize;


//    绘制时控制文本绘制的范围
    private Rect mBound;
    private Paint mPaint;


    public CustomTextView(Context context) {
        this(context,null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        AttributeSet 是一个集合

        int count = attrs.getAttributeCount() ;
        for (int i=0;i<count;i++) {
            String name =  attrs.getAttributeName(i);
            String value = attrs.getAttributeValue(i);
//            取出所有属性 key value
            System.out.println("name = " + name + " value "+ value);
//            int widthDimensionId =  attrs.getAttributeResourceValue(3, -1);
//            System.out.println("widthDimensionId = " + widthDimensionId);
        }

//        如果布局中的属性的值是引用类型（比如：@dimen/dp100，@string/aa），如果使用AttributeSet去获得最终的像素值，
// 那么需要第一步拿到id，第二步再去解析id。而TypedArray正是帮我们简化了这个过程。


        TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        mText = typedArray.getString(R.styleable.CustomTextView_customText);
        mTextColor = typedArray.getColor(R.styleable.CustomTextView_customTextColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTextView_customTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mBound = new Rect();
        // 计算了描绘字体需要的范围
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("widthMeasureSpec = " + widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;

//        MeasureSpec.AT_MOST wrap_content
        if (widthMode == MeasureSpec.EXACTLY)
        {
            // match_parent  currentsize
            width = widthSize;
        } else
        {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }


        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("widthMeasureSpec = onDraw" );



        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }


}
