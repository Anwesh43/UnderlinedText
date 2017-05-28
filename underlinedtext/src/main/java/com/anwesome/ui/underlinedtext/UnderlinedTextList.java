package com.anwesome.ui.underlinedtext;

import android.app.Activity;
import android.widget.HorizontalScrollView;

/**
 * Created by anweshmishra on 29/05/17.
 */

public class UnderlinedTextList {
    private Activity activity;
    private HorizontalScrollView horizontalScrollView;
    private boolean isShown = false;
    public UnderlinedTextList(Activity activity) {
        this.activity = activity;
        horizontalScrollView = new HorizontalScrollView(activity);
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(horizontalScrollView);
            isShown = true;
        }
    }
    public void addText(String text) {
        if(!isShown) {

        }
    }
}
