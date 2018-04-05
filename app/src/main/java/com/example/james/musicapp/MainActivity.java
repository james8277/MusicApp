package com.example.james.musicapp;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button Button_metronome;


    int streamMetronome;




    private boolean isMetronome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = (Button)findViewById(R.id.Button_1);
        button_2 = (Button)findViewById(R.id.Button_2);
        button_3 = (Button)findViewById(R.id.Button_3);
        button_4 = (Button)findViewById(R.id.Button_4);

//        AudioAttributes test =
//                new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

        final SoundPool soundPool_test = new SoundPool.Builder().setMaxStreams(10).build();

        final int soundID_1 = soundPool_test.load(MainActivity.this,R.raw.hihat_1,1);
        final int soundID_2 = soundPool_test.load(MainActivity.this,R.raw.drum_1,1);
        final int soundID_3 = soundPool_test.load(MainActivity.this,R.raw.drum_2,1);
        final int soundID_4 = soundPool_test.load(MainActivity.this,R.raw.bassdrum_1,1);
        final int soundID_5 = soundPool_test.load(MainActivity.this,R.raw.hihat_2,1);



        Button_metronome = (Button)findViewById(R.id.Button_metronome);
        Button_metronome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: metronome");
                if(!isMetronome)
                {
                    float soundRate = (float)1.0;
                    Log.d(TAG, "onClick: start");
                    isMetronome = true;
                    streamMetronome = soundPool_test.play(soundID_1,1,1,1,-1,soundRate);
//                    thread_metronome.start();
//                    metronome.start();
//                    metronome.setLooping(true);

                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    isMetronome = false;
                    soundPool_test.stop(streamMetronome);
//                    metronome.stop();
//                    metronome.setLooping(false);
//                    metronome.prepareAsync();
                }

            }
        });



        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button1");
                soundPool_test.play(soundID_1,1,1,1,0,1);
//                MediaPlayer drum_1 = MediaPlayer.create(MainActivity.this,R.raw.hihat_2);
//                drum_1.start();
//                drum_1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                    };
//                });
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button2");
                soundPool_test.play(soundID_2,1,1,1,0,1);

//                MediaPlayer drum_2 = MediaPlayer.create(MainActivity.this,R.raw.drum_1);
//                drum_2.start();
//                drum_2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                    };
//                });
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button3");
                soundPool_test.play(soundID_3,1,1,1,0,1);
//                MediaPlayer drum_3 = MediaPlayer.create(MainActivity.this,R.raw.drum_2);
//                drum_3.start();
//                drum_3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                    };
//                });
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button4");
                soundPool_test.play(soundID_4,1,1,1,0,1);

//                MediaPlayer drum_4 = MediaPlayer.create(MainActivity.this,R.raw.bassdrum_2);
//                drum_4.start();
//                drum_4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                    };
//                });
            }
        });
    }
}
