package com.anwesome.ui.underlinedtextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.underlinedtext.UnderlinedTextList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String text = "Malcolm James McCormick, best known by his stage name Mac Miller, is an American rapper";
        UnderlinedTextList underlinedTextList = new UnderlinedTextList(this);
        for(String token:text.split(" ")) {
            underlinedTextList.addText(token);
        }
        underlinedTextList.show();
    }
}
