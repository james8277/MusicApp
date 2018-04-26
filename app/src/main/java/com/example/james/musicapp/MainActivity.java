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

import java.util.ArrayList;

import fragment.MainFragment;
import fragment.Training;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DataBase dataBase;
    private ArrayList<Integer[]> arrayList_song_example_easy;


    public void addExampleSong()
    {
        arrayList_song_example_easy = new ArrayList<Integer[]>();
        for(int i=0;i<4;i++)
        {
            Integer temp_int_array_1[] = new Integer[2];
            temp_int_array_1[0] = 1000*(i)*60/80;
            temp_int_array_1[1] = 0;
            arrayList_song_example_easy.add(temp_int_array_1);
        }
        for(int i=4;i<36;i++)
        {
            Integer temp_int_array_1[] = new Integer[2];
            temp_int_array_1[0] = 1000*(i)*60/80;
            temp_int_array_1[1] = 1;
            arrayList_song_example_easy.add(temp_int_array_1);
            if(i%4 == 0)
            {
                Integer temp_int_array_2[] = new Integer[2];
                temp_int_array_2[0] = 1000*(i)*60/80;
                temp_int_array_2[1] = 4;
                arrayList_song_example_easy.add(temp_int_array_2);
            }
            if(i%4 == 2)
            {
                Integer temp_int_array_3[] = new Integer[2];
                temp_int_array_3[0] = 1000*(i)*60/80;
                temp_int_array_3[1] = 3;
                arrayList_song_example_easy.add(temp_int_array_3);
            }
        }

//        Log.e(TAG, "addExampleSong: size" + arrayList_song_example_easy.size() );
        String temp[][] = new String[2][arrayList_song_example_easy.size()];
        for(int i=0;i<arrayList_song_example_easy.size();i++)
        {
            temp[0][i] = arrayList_song_example_easy.get(i)[0].toString();
            temp[1][i] = arrayList_song_example_easy.get(i)[1].toString();
        }
        dataBase.addSong(temp,"Example song easy " + dataBase.getSongNumber());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this,"Database Record",null, 1);

        addExampleSong();


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
