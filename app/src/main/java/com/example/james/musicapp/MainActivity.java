package com.example.james.musicapp;

import android.app.Fragment;
import android.app.FragmentController;
import android.app.FragmentTransaction;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fragment.MainFragment;
import fragment.Training;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DataBase dataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this,"Database Record",null, 1);


        FragmentTransaction mf = getFragmentManager().beginTransaction();
        Fragment fragment_start = new MainFragment();
        mf.replace(R.id.container_main,fragment_start);
        mf.commit();
    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if(keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            Log.d(TAG, "onKeyDown: BACK");
//            if(getFragmentManager().getBackStackEntryCount() > 0)
//            {
//                Log.d(TAG, "Fragment Count > 0");
//                getFragmentManager().popBackStack();
//            }
//            else
//            {
//                return super.onKeyDown(keyCode,event);
//            }
//        }
//
//        return false;
//    }
}
