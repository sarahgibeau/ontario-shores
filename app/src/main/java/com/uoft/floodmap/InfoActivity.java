package com.uoft.floodmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    TextView link_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        link_text = findViewById(R.id.textView);
        link_text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
