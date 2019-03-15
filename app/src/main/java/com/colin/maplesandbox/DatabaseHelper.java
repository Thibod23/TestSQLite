package com.colin.maplesandbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";

    private static final String tbl_people = "tbl_people";

    public DatabaseHelper(Context context){
        super(context, tbl_people, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + tbl_people + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "name" + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP IF TABLE EXISTS " + tbl_people);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item);

        Log.d(TAG, "addData: Adding " + item + " to " + tbl_people);

        long result = db.insert(tbl_people, null, contentValues);

        //if date is inserted incorrectly it will return -1
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tbl_people;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
