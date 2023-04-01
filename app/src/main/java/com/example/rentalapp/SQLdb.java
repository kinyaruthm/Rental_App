package com.example.rentalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLdb extends SQLiteOpenHelper  {
    public  static  final String dbName="rental";
    public  static  final int version=2;

    public static final String TABLE_USER = "loggedUser";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_USER_PASSWORD = "Password";

    public static final String COLUMN_USER_USERNAME = "Username";
    public String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "( "+COLUMN_USER_ID +" INTEGER PRIMARY KEY,"
            + COLUMN_USER_PASSWORD + " VARCHAR," + COLUMN_USER_USERNAME + "VARCHAR, logged INTEGER )";


    public SQLdb(Context context) {
        super(context, dbName, null, version);
         }


    public void onCreate(SQLdb db) {

        db.execSQL(CREATE_USER_TABLE);
    }

    private void execSQL(String create_user_table) {
    }


    public void onUpgrade(SQLdb db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
    public boolean insertUsers(String Password, String Username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_USER_PASSWORD, Password);
        contentValues.put(COLUMN_USER_USERNAME, Username);

        contentValues.put("logged", 1);

        long result=db.insertOrThrow(TABLE_USER, null, contentValues);
        if (result>0){
            return true;
        }else {
            return false;
        }

    }
    public Cursor syncDetails() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        try {
            String sql= ("SELECT * FROM loggedUser where logged=1");
            cursor= db.rawQuery(sql, null);
            if (cursor.getCount()>0) {
                cursor.moveToFirst();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cursor.close();
        db.close();
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}