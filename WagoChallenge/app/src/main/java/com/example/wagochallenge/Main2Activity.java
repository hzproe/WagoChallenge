package com.example.wagochallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        LinearGradient test = new LinearGradient(0.f, 0.f, 1000.f, 0.0f,

                new int[] { 0xFF0000FF, 0xFF00FF00,  0xFFFF0000},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(test);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                TextView txtView = (TextView)findViewById(R.id.textView3);
                txtView.setText( "New Temperature: "+seekBar.getProgress() + " °C");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
