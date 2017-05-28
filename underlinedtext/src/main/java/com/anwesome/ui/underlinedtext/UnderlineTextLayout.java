package com.anwesome.ui.underlinedtext;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 29/05/17.
 */

public class UnderlineTextLayout extends ViewGroup{
    private int w,h;
    private IndicatorView indicator;
    public UnderlineTextLayout(Context context) {
        super(context);
        initDimension(context);

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
            measureChild(child,wspec,hspec);
            viewW += (child.getMeasuredWidth()+w/20);
            viewH = Math.max(viewH,child.getMeasuredHeight());


        }
        setMeasuredDimension(viewW,w/20+viewH*2);
    }
    public void addIndicator() {
        indicator = new IndicatorView(getContext());
        addView(indicator,new LayoutParams(w/20,h/60));
        requestLayout();
    }
    public void addText(String text) {
       
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int x = w/20,y = w/20;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(x,y,x+child.getMeasuredWidth(),y+child.getMeasuredHeight());
            x += (child.getMeasuredWidth()+w/20);
        }
    }

}
