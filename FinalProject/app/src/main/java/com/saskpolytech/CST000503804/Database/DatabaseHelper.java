package com.saskpolytech.CST000503804.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saskpolytech.CST000503804.Adapter.ActivityItem;
import com.saskpolytech.CST000503804.Adapter.GoalItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitness_tracker.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_BMI = "bmi";


    private static final String TABLE_ACTIVITIES = "activities";
    private static final String COLUMN_ACTIVITY_ID = "id";
    public static final String COLUMN_ACTIVITY_NAME = "name";
    public static final String COLUMN_ACTIVITY_DETAILS = "details";

    private static final String TABLE_GOALS = "goals";
    private static final String COLUMN_GOAL_ID = "id";
    private static final String COLUMN_GOAL_NAME = "name";
    private static final String COLUMN_GOAL_DETAILS = "details";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_BMI + " REAL"
            + ")";

    private static final String CREATE_TABLE_ACTIVITIES = "CREATE TABLE " + TABLE_ACTIVITIES + "("
            + COLUMN_ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ACTIVITY_NAME + " TEXT,"
            + COLUMN_ACTIVITY_DETAILS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_GOALS = "CREATE TABLE " + TABLE_GOALS + "("
            + COLUMN_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_GOAL_NAME + " TEXT,"
            + COLUMN_GOAL_DETAILS + " TEXT"
            + ")";
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ACTIVITIES);
        db.execSQL(CREATE_TABLE_GOALS);
    }

    public void reCreateTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        onCreate(db);
    }

    public long addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        long userId = db.insert(TABLE_USERS, null, values);
        db.close();
        return userId;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public void addActivity(String name, String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITY_NAME, name);
        values.put(COLUMN_ACTIVITY_DETAILS, details);
        db.insert(TABLE_ACTIVITIES, null, values);
        db.close();
    }

    public List<ActivityItem> getAllActivities() {
        List<ActivityItem> activityList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ACTIVITIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_NAME));
                @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DETAILS));
                ActivityItem item = new ActivityItem(name, details);
                activityList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return activityList;
    }

    public void updateActivity(String name, String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITY_NAME, name);
        values.put(COLUMN_ACTIVITY_DETAILS, details);
        db.update(TABLE_ACTIVITIES, values, COLUMN_ACTIVITY_NAME + " = ?", new String[]{name});
        db.close();
    }

    public void deleteActivity(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITIES, COLUMN_ACTIVITY_NAME + " = ?", new String[]{name});
        db.close();
    }

    public void addGoal(GoalItem goalItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GOAL_NAME, goalItem.getName());
        values.put(COLUMN_GOAL_DETAILS, goalItem.getDetails());
        db.insert(TABLE_GOALS, null, values);
        db.close();
    }

    public List<GoalItem> getAllGoals() {
        List<GoalItem> goalList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_GOALS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_GOAL_NAME));
                @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex(COLUMN_GOAL_DETAILS));
                GoalItem item = new GoalItem(name, details);
                goalList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return goalList;
    }

    public void updateGoal(GoalItem goalItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GOAL_NAME, goalItem.getName());
        values.put(COLUMN_GOAL_DETAILS, goalItem.getName());
        db.update(TABLE_GOALS, values, COLUMN_GOAL_NAME + " = ?", new String[]{goalItem.getName()});
        db.close();
    }

    public void deleteGoal(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GOALS, COLUMN_GOAL_NAME + " = ?", new String[]{name});
        db.close();
    }

    public long addBmi(double bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_BMI, bmi);
        long rowId = db.insert(TABLE_USERS, null, values);
        db.close();
        return rowId;
    }

    @SuppressLint("Range")
    public double getBmi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_BMI};
        Cursor cursor = db.query(TABLE_USERS, columns, null, null, null, null, null);
        double bmi = 0.0;
        if (cursor.moveToFirst()) {
            bmi = cursor.getDouble(cursor.getColumnIndex(COLUMN_USER_BMI));
        }
        cursor.close();
        db.close();
        return bmi;
    }

}