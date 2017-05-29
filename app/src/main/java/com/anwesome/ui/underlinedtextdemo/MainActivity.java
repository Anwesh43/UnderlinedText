package com.anwesome.ui.underlinedtextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.underlinedtext.OnClickListener;
import com.anwesome.ui.underlinedtext.UnderlinedTextList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String text = "Malcolm James McCormick, best known by his stage name Mac Miller, is an American rapper";
        UnderlinedTextList underlinedTextList = new UnderlinedTextList(this);
        for(String token:text.split(" ")) {
            final String currToken = token;
            underlinedTextList.addText(token, new OnClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, String.format("%s clicked",currToken), Toast.LENGTH_SHORT).show();
                }
            });
        }
        underlinedTextList.show();
    }
}
