package com.anwesome.ui.underlinedtext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.support.v7.widget.AppCompatTextView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by anweshmishra on 29/05/17.
 */

public class UnderlineTextLayout extends ViewGroup{
    private int w,h;
    private IndicatorView indicator;
    private AnimationHandler animationHandler;
    public UnderlineTextLayout(Context context) {
        super(context);
        initDimension(context);
        animationHandler = new AnimationHandler();
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void onMeasure(int wspec,int hspec) {
        int viewW = w/20,viewH = 0;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            if(child instanceof UnderlinedTextView) {
                measureChild(child, wspec, hspec);
                viewW += (child.getMeasuredWidth() + w / 20);
                viewH = Math.max(viewH, child.getMeasuredHeight());
            }
        }
        setMeasuredDimension(viewW,w/20+viewH*2);
    }
    public void addIndicator() {
        indicator = new IndicatorView(getContext());
        if(getChildCount() > 0) {
            UnderlinedTextView firstTextView = (UnderlinedTextView) getChildAt(0);
            indicator.initX(firstTextView.getX()+firstTextView.getMeasuredWidth()/2-w/30);
        }
        addView(indicator,new LayoutParams(w/15,h/60));
        requestLayout();
    }
    public void addText(String text) {
        UnderlinedTextView underlinedTextView = new UnderlinedTextView(getContext(),text);
        addView(underlinedTextView,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        requestLayout();
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int x = w/20,y = w/20;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            if(child instanceof UnderlinedTextView) {
                child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
                x += (child.getMeasuredWidth() + w / 20);
            }
            else if(child instanceof IndicatorView){
                child.layout((int)child.getX(),y+h/15,(int)child.getX()+child.getMeasuredWidth(),y+h/15+child.getMeasuredHeight());
            }
        }
    }
    private class UnderlinedTextView extends AppCompatTextView {
        public UnderlinedTextView(Context context,String text) {
            super(context);
            setTextSize(h/20);
            setText(text);
            setTextColor(Color.BLACK);
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationHandler.startAnimation(indicator.getX(),getX()+getMeasuredWidth()/2-indicator.getMeasuredWidth()/2);
            }
            return true;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator valueAnimator;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(indicator != null) {
                indicator.update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(valueAnimator != null) {
                valueAnimator = null;
            }
        }
        public void startAnimation(float from,float to) {
            if(valueAnimator == null) {
                valueAnimator = ValueAnimator.ofFloat(from, to);
                valueAnimator.setDuration(700);
                valueAnimator.addUpdateListener(this);
                valueAnimator.addListener(this);
                valueAnimator.start();
            }
        }

    }
}
