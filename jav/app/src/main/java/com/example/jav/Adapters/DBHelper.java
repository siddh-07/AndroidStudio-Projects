package com.example.jav.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.jav.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

       final static String DBName = "app.db";
       final static int DBVersion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "Create table orders"+
                        "(id Integer primary key autoincrement,"+
                        "name text,"+
                        "price text,"+
                        "image int,"+
                        "cals text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists orders");
        onCreate(db);
    }
    public boolean insertOrder(String name, String price, int image, String cals){
        SQLiteDatabase database =getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("price", price);
        values.put("image", image);
        values.put("cals", cals);


        long id = database.insert("orders", null, values);
        if(id <= 0){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<OrderModel> getOrder() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT  id,name, image, price FROM orders", null);
        if (cursor.moveToFirst()) {
            do {
                OrderModel model = new OrderModel();
                model.setOrderId(cursor.getInt(0)+"");
                model.setOrderName(cursor.getString(1) + "");
                model.setOrderImage(cursor.getInt(2));
                model.setOrderPrice(cursor.getInt(3) + "");
                orders.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return orders;
    }
}
