package com.example.tan.colormatch;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.graphics.Color;
import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by mew2795 on 4/15/16.
 * useful websites
 * http://developer.android.com/training/custom-views/custom-drawing.html
 * http://developer.android.com/guide/topics/graphics/2d-graphics.html

public class Myview {
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the shadow
        canvas.drawOval(
                mShadowBounds,
                mShadowPaint
        );

        // Draw the label text
        canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);

        // Draw the pie slices
        for (int i = 0; i < mData.size(); ++i) {
            ClipData.Item it = mData.get(i);
            mPiePaint.setShader(it.mShader);
            canvas.drawArc(mBounds,
                    360 - it.mEndAngle,
                    it.mEndAngle - it.mStartAngle,
                    true, mPiePaint);
        }

        // Draw the pointer
        canvas.drawLine(mTextX, mPointerY, mPointerX, mPointerY, mTextPaint);
        canvas.drawCircle(mPointerX, mPointerY, mPointerSize, mTextPaint);
    }
}
*/
