package com.uoft.floodmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    Button b2;
    Animation fade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView5);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade_animation);
        text.setAnimation(fade);


        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    public void onClick(View v) {

        Intent intent = new Intent(this, MapActivity.class);
        if (v.getId() == b1.getId()) {
            Log.i("app", "Button 1 clicked");
            intent.putExtra("scenario", "low");
        } else {
            Log.i("app", "Button 2 clicked");
            intent.putExtra("scenario", "high");
        }
        startActivity(intent);

    }

}
