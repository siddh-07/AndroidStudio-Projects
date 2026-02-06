package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="demo.db";
    public DBHelper(Context context) {
        super(context, "demo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String id, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id",id);
        values.put("password",password);

        long result=db.insert("users",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkid(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where id=?", new String[] {id});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkidpasswrd(String id, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where id=?, password=?", new String[] {id,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
