package com.example.james.musicapp;

import android.app.Fragment;
import android.app.FragmentController;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fragment.MainFragment;
import fragment.Training;
import fragment.Welcome;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DataBase dataBase;

    private void createSunnyDay() {
        ArrayList<Integer[]> arrayList_sunny_day = new ArrayList<Integer[]>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getAssets().open("sunnyday.txt")));

            String mLine = reader.readLine();
            while(mLine != null)
            {
                String temp[] = mLine.split("\t");
//                Log.e(TAG, "mLine = \t" + Integer.valueOf(temp[0]) + "\t" + Integer.valueOf(temp[1]));
                Integer temp_int_array_1[] = new Integer[2];
                temp_int_array_1[0] = 1000*(Integer.valueOf(temp[1]))*60/(68*2);
//                Log.e(TAG, "Time = \t" + temp_int_array_1[0]);
                temp_int_array_1[1] = Integer.valueOf(temp[0]);
                arrayList_sunny_day.add(temp_int_array_1);
                mLine = reader.readLine();

            }
            String temp[][] = new String[2][arrayList_sunny_day.size()];
            for(int i = 0; i< arrayList_sunny_day.size(); i++)
            {
                temp[0][i] = arrayList_sunny_day.get(i)[0].toString();
                temp[1][i] = arrayList_sunny_day.get(i)[1].toString();
            }
            dataBase.addSong(temp,"Sunny day");


//            Log.e(TAG, "Array Length = \t" + arrayList_sunny_day.size());

        } catch (IOException e) {
            Log.e(TAG, "Open Fail");
            e.printStackTrace();
        }
    }

    private void createCanon() {
        ArrayList<Integer[]> arrayList_canon = new ArrayList<Integer[]>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getAssets().open("canon.txt")));

            String mLine = reader.readLine();
            while(mLine != null)
            {
                String temp[] = mLine.split("\t");
//                Log.e(TAG, "mLine = \t" + Integer.valueOf(temp[0]) + "\t" + Integer.valueOf(temp[1]));
                Integer temp_int_array_1[] = new Integer[2];
                temp_int_array_1[0] = 1000*(Integer.valueOf(temp[1]))*60/80;
//                Log.e(TAG, "Time = \t" + temp_int_array_1[0]);
                temp_int_array_1[1] = Integer.valueOf(temp[0]);
                arrayList_canon.add(temp_int_array_1);
                mLine = reader.readLine();

            }
            String temp[][] = new String[2][arrayList_canon.size()];
            for(int i = 0; i< arrayList_canon.size(); i++)
            {
                temp[0][i] = arrayList_canon.get(i)[0].toString();
                temp[1][i] = arrayList_canon.get(i)[1].toString();
            }
            dataBase.addSong(temp,"Canon");


//            Log.e(TAG, "Array Length = \t" + arrayList_sunny_day.size());

        } catch (IOException e) {
            Log.e(TAG, "Open Fail");
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this,"Database Record",null, 1);
        dataBase.deleteTableSong();

        AddSong createSong = new AddSong();
        String temp[][] = createSong.getEasyExampleSong();
        dataBase.addSong(temp,"Example Song: Easy");

        temp = createSong.getNormalExampleSong();
        dataBase.addSong(temp,"Example Song: Normal");

        createSunnyDay();
        createCanon();


        FragmentTransaction mf = getFragmentManager().beginTransaction();
        Fragment fragment_start = new Welcome();
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
