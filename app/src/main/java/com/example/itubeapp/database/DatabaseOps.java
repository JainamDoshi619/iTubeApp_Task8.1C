package com.example.itubeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

import com.example.itubeapp.Util.LogInInfoStore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseOps extends SQLiteOpenHelper {


    public DatabaseOps(@Nullable Context context) {
        super(context,"iTube_Player_database",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,FULLNAME TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        String CREATE_VIDEO_TABLE = "CREATE TABLE VIDEOS(VIDEOID INTEGER PRIMARY KEY AUTOINCREMENT, VIDEOLINK TEXT)";
        sqLiteDatabase.execSQL(CREATE_VIDEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        String DROP_VIDEO_TABLE = "DROP TABLE IF EXISTS VIDEOS";
        sqLiteDatabase.execSQL(DROP_VIDEO_TABLE);
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }
    public long insertUserInfo(LogInInfoStore logInInfoStore){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",logInInfoStore.getUsername());
        contentValues.put("PASSWORD",logInInfoStore.getPassword());
        contentValues.put("FULLNAME",logInInfoStore.getFullName());

        long exec = sqLiteDatabase.insert("USERS",null,contentValues);
        sqLiteDatabase.close();
        return exec;

    }
    public Boolean checkLogIn(String Username, String Password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String select_query = "SELECT * FROM USERS";
        Cursor cursor = sqLiteDatabase.rawQuery(select_query,null);
        if(cursor.moveToFirst()){
            do {
                LogInInfoStore temp = new LogInInfoStore(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                if(temp.getUsername().equals(Username) && temp.getPassword().equals(Password)){
                    return true;

                }
            }while (cursor.moveToNext());
        }
        return false;
    }
    public void insertVideo(String playLink){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("VIDEOLINK",playLink);
        sqLiteDatabase.insert("VIDEOS",null,contentValues);
        sqLiteDatabase.close();
    }
    public ArrayList<String> fetchAll(){
        ArrayList<String> videos = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String All_Videos = "SELECT * FROM VIDEOS";
        Cursor cursor = sqLiteDatabase.rawQuery(All_Videos,null);
        if(cursor.moveToFirst()){
            do {
                String temp = cursor.getString(1);
                videos.add(temp);
            }while (cursor.moveToNext());

        }
        sqLiteDatabase.close();
        return videos;
    }
}
