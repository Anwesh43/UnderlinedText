package com.anwesome.ui.underlinedtext;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by anweshmishra on 29/05/17.
 */

public class UnderlinedTextList {
    private Activity activity;
    private HorizontalScrollView horizontalScrollView;
    private boolean isShown = false;
    private UnderlineTextLayout underlineTextLayout;
    public UnderlinedTextList(Activity activity) {
        this.activity = activity;
        horizontalScrollView = new HorizontalScrollView(activity);
        underlineTextLayout = new UnderlineTextLayout(activity);
        horizontalScrollView.addView(underlineTextLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void show() {
        if(!isShown) {
            underlineTextLayout.addIndicator();
            activity.setContentView(horizontalScrollView);
            isShown = true;
        }
    }
    public void addText(String text,OnClickListener onClickListener) {
        if(!isShown) {
            underlineTextLayout.addText(text,onClickListener);
        }
    }
}
