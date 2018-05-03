package com.example.james.musicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class DataBase extends SQLiteOpenHelper {


    private static final String TAG = "DataBase Created";
    private String TABLE_RECORD = "recordData";
    private String TABLE_SONG = "songData";
    private String KEY_ID = "_ID";
    private String KEY_NAME = "record_name";
    private String KEY_TRACK = "record_data";
    private String KEY_TRACK_TIME = "record_time";
    private String KEY_SONG_NAME = "song_name";
    private String KEY_SONG_TRACK = "song_data";
    private String KEY_SONG_TRACK_TIME = "song_time";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        Log.e(TAG, "DataBase: Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: created");

        String sql_all = "CREATE TABLE IF NOT EXISTS recordData" +
                "(" + "_ID INTEGER PRIMARY KEY" + ",record_name VARCHAR(20)" + ", record_data VARCHAR(3)" + ", record_time VARCHAR(10)" + ")";

        String sql_song = "CREATE TABLE IF NOT EXISTS songData" +
                "(" + "_ID INTEGER PRIMARY KEY" + ",song_name VARCHAR(20)" + ", song_data VARCHAR(3)" + ", song_time VARCHAR(10)" + ")";

        db.execSQL(sql_all);
        db.execSQL(sql_song);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static String strSeparator = "__,__";
    private static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    private static String[] convertStringToArray(String str){

        return str.split(strSeparator);
    }





    public void addRecord(String[][] record_track, String record_name)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        SQLiteDatabase database_read = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = database_read.rawQuery("SELECT _ID FROM recordData",null);

        int tmp_id_number;

        if(cursor.moveToLast())
        {
            tmp_id_number = cursor.getInt(0);
            tmp_id_number++;
        }
        else
        {
            tmp_id_number = 0;
        }

//        Log.e(TAG, "record track length =  " + record_track[0]);
//        for(int i=0;i<record_track[0].length;i++)
//        {
//            Log.e(TAG, "\t" +record_track[0][i]);
//        }

        String temp_track_time = convertArrayToString(record_track[0]);
        String temp_track = convertArrayToString(record_track[1]);

//        Log.e(TAG, "track =\t" + temp_track);
//        Log.e(TAG, "track_time =\t" + temp_track_time);

        cv.put(KEY_ID,tmp_id_number);
        cv.put(KEY_NAME,record_name);
        cv.put(KEY_TRACK,temp_track);
        cv.put(KEY_TRACK_TIME,temp_track_time);


        database.insert(TABLE_RECORD,null,cv);
    }
    public String[] getRecordName()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_name[] = new String[getRecordNumber_record()];
        Cursor cursor = database.rawQuery("SELECT" + " record_name" + " FROM recordData", null);
        cursor.moveToFirst();
//        Log.e(TAG, "getRecordName: number" + getRecordNumber());
        for(int i=0;i<getRecordNumber_record();i++)
        {
            temp_name[i] = cursor.getString(0);
            cursor.moveToNext();
        }
        return temp_name;
    }
    public int[] getRecordData(int position)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_data[];
        Cursor cursor = database.rawQuery("SELECT" + " record_data" + " FROM recordData", null);
        cursor.moveToFirst();
//        Log.e(TAG, "cursor = \t" + cursor.getString(0));
        for(int i=0;i<position;i++)
        {
            cursor.moveToNext();
        }
        temp_data = convertStringToArray(cursor.getString(0));

        int[] temp_int = new int[temp_data.length];
        for(int i=0;i<temp_data.length;i++)
        {
            temp_int[i] = Integer.parseInt(temp_data[i]);
        }
        return temp_int;

    }
    public int[] getRecordTime(int position)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_time[];
        Cursor cursor = database.rawQuery("SELECT" + " record_time" + " FROM recordData", null);
        cursor.moveToFirst();
        for(int i=0;i<position;i++)
        {
            cursor.moveToNext();
        }
        temp_time = convertStringToArray(cursor.getString(0));

        int[] temp_int = new int[temp_time.length];
        for(int i=0;i<temp_time.length;i++)
        {
            temp_int[i] = Integer.parseInt(temp_time[i]);
        }
        return temp_int;
    }
    public int getRecordNumber_record()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _ID FROM recordData",null);

        if(c.moveToLast())
        {
            return c.getInt(0)+1;
        }
        else
        {
            return 0;
        }

    }
    public void deleteTableRecord()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w("db_delete_db_all","delete");
        db.delete(TABLE_RECORD, null, null);
    }


    public void addSong(String[][] song_track, String song_name)
    {
        if(getSongNumber() != 2)
        {
            Log.e(TAG, "addSong: song" + song_name);
            SQLiteDatabase database = this.getWritableDatabase();
            SQLiteDatabase database_read = this.getReadableDatabase();
            ContentValues cv = new ContentValues();
            Cursor cursor = database_read.rawQuery("SELECT _ID FROM songData",null);

            int tmp_id_number;

            if(cursor.moveToLast())
            {
                tmp_id_number = cursor.getInt(0);
                tmp_id_number++;
            }
            else
            {
                tmp_id_number = 0;
            }

            String temp_track_time = convertArrayToString(song_track[0]);
            String temp_track = convertArrayToString(song_track[1]);

            cv.put(KEY_ID,tmp_id_number);
            cv.put(KEY_SONG_NAME,song_name);
            cv.put(KEY_SONG_TRACK,temp_track);
            cv.put(KEY_SONG_TRACK_TIME,temp_track_time);


            database.insert(TABLE_SONG,null,cv);
        }
    }
    public String[] getSongName()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_name[] = new String[getSongNumber()];
        Cursor cursor = database.rawQuery("SELECT" + " song_name" + " FROM songData", null);
        cursor.moveToFirst();
//        Log.e(TAG, "getSongName: number:\t" + getSongNumber());
        for(int i=0;i<getSongNumber();i++)
        {
            temp_name[i] = cursor.getString(0);
            cursor.moveToNext();
        }
//        Log.e(TAG, "Song Name:\t" + temp_name[0] + "\t" + temp_name[1]);
        return temp_name;
    }
    public int[] getSongData(int position)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_data[];
        Cursor cursor = database.rawQuery("SELECT" + " song_data" + " FROM songData", null);
        cursor.moveToFirst();
//        Log.e(TAG, "cursor = \t" + cursor.getString(0));
        for(int i=0;i<position;i++)
        {
            cursor.moveToNext();
        }
        temp_data = convertStringToArray(cursor.getString(0));

        int[] temp_int = new int[temp_data.length];
        for(int i=0;i<temp_data.length;i++)
        {
            temp_int[i] = Integer.parseInt(temp_data[i]);
        }
        return temp_int;

    }
    public int[] getSongTime(int position)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String temp_time[];
        Cursor cursor = database.rawQuery("SELECT" + " song_time" + " FROM songData", null);
        cursor.moveToFirst();
        for(int i=0;i<position;i++)
        {
            cursor.moveToNext();
        }
        temp_time = convertStringToArray(cursor.getString(0));

        int[] temp_int = new int[temp_time.length];
        for(int i=0;i<temp_time.length;i++)
        {
            temp_int[i] = Integer.parseInt(temp_time[i]);
        }
        return temp_int;
    }
    public int getSongNumber()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _ID FROM songData",null);

        if(c.moveToLast())
        {
            return c.getInt(0)+1;
        }
        else
        {
            return 0;
        }

    }



}
