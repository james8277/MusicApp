package com.example.james.musicapp;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

public class AddSong {

    private ArrayList<Integer[]> arrayList_song_example_easy;
    private ArrayList<Integer[]> arrayList_song_example_normal;

    private static final String TAG = "AddSong";


    public AddSong() {

        arrayList_song_example_easy = new ArrayList<Integer[]>();
        arrayList_song_example_normal = new ArrayList<Integer[]>();
        createEasyExampleSong();
        createNormalExampleSong();

    }

    private void createEasyExampleSong()
    {
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

    }

    private void createNormalExampleSong()
    {
        for(int i=0;i<4;i++)
        {
            Integer temp_int_array_1[] = new Integer[2];
            temp_int_array_1[0] = 1000*(i)*60/120;
            temp_int_array_1[1] = 0;
            arrayList_song_example_normal.add(temp_int_array_1);
        }
        for(int i=4;i<36;i++)
        {
            Integer temp_int_array_1[] = new Integer[2];
            temp_int_array_1[0] = 1000*(i)*60/120;
            temp_int_array_1[1] = 1;
            arrayList_song_example_normal.add(temp_int_array_1);
            if((i+4)%8 == 0)
            {
                Integer temp_int_array_2[] = new Integer[2];
                temp_int_array_2[0] = 1000*(i)*60/120;
                temp_int_array_2[1] = 4;
                arrayList_song_example_normal.add(temp_int_array_2);
            }
            if((i+4)%8 == 3)
            {
                Integer temp_int_array_2[] = new Integer[2];
                temp_int_array_2[0] = 1000*(i)*60/120;
                temp_int_array_2[1] = 4;
                arrayList_song_example_normal.add(temp_int_array_2);
            }
            if((i+4)%8 == 5)
            {
                Integer temp_int_array_2[] = new Integer[2];
                temp_int_array_2[0] = 1000*(i)*60/120;
                temp_int_array_2[1] = 4;
                arrayList_song_example_normal.add(temp_int_array_2);
            }
            if(i%4 == 2)
            {
                Integer temp_int_array_3[] = new Integer[2];
                temp_int_array_3[0] = 1000*(i)*60/120;
                temp_int_array_3[1] = 3;
                arrayList_song_example_normal.add(temp_int_array_3);
            }
        }

//        Log.e(TAG, "addExampleSong: size" + arrayList_song_example_easy.size() );
    }

    private void createSunnyDay()
    {

    }


    public String[][] getEasyExampleSong()
    {
        String temp[][] = new String[2][arrayList_song_example_easy.size()];
        for(int i = 0; i< arrayList_song_example_easy.size(); i++)
        {
            temp[0][i] = arrayList_song_example_easy.get(i)[0].toString();
            temp[1][i] = arrayList_song_example_easy.get(i)[1].toString();
        }

        return temp;
    }


    public String[][] getNormalExampleSong()
    {

        String temp[][] = new String[2][arrayList_song_example_normal.size()];
        for(int i = 0; i< arrayList_song_example_normal.size(); i++)
        {
            temp[0][i] = arrayList_song_example_normal.get(i)[0].toString();
            temp[1][i] = arrayList_song_example_normal.get(i)[1].toString();
        }

        return temp;
    }

}
