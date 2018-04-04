package com.example.james.musicapp;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = (Button)findViewById(R.id.Button_1);
        button_2 = (Button)findViewById(R.id.Button_2);
        button_3 = (Button)findViewById(R.id.Button_3);
        button_4 = (Button)findViewById(R.id.Button_4);

        final MediaPlayer drum_1 = MediaPlayer.create(this,R.raw.drum_1);
        final MediaPlayer drum_2 = MediaPlayer.create(this,R.raw.drum_2);
        final MediaPlayer drum_3 = MediaPlayer.create(this,R.raw.drum_3);
        final MediaPlayer drum_4 = MediaPlayer.create(this,R.raw.drum_4);


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button1");
                drum_1.start();
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button2");
                drum_2.start();
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button3");
                drum_3.start();
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button4");
                drum_4.start();
            }
        });
    }
}
