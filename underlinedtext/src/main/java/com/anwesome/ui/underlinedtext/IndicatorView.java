package com.anwesome.ui.underlinedtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by anweshmishra on 29/05/17.
 */

public class IndicatorView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public IndicatorView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#00E676"));
        int w = canvas.getWidth(),h = canvas.getHeight();
        canvas.drawRoundRect(new RectF(0,0,w,h),w/5,w/5,paint);
    }
    public void update(float value) {
        setX(value);
    }
}
