package com.example.tan.colormatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tan on 4/5/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // declare the DB name here
    public static final String DATABASE_NAME = "Highscores.db";

    // now declare the Table name that will be part of the DB
    public static final String TABLE_NAME = "Highscores_table";

    // declare the columns of the table
    public static final String COL_1 = "PLAYER_NAME";
    public static final String COL_2 = "BEST_TIME";
    public static final String COL_5 = "ID";

    // this is referencing the java class that will manage the SQL DB
    public DatabaseHelper(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (PLAYER_NAME TEXT, BEST_TIME TEXT, ID INTEGER PRIMARY KEY AUTOINCREMENT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // update the table if version number is increased and call onCreate to create new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String time) {
        // open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // this class is used to store a set of values that a ContentResolver can process
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, time);

        // need to give this the table name and the content values
        long result = db.insert(TABLE_NAME, null, contentValues);

        // check if the insert works or not
        if (result == -1) return false;
        else return true;
    }

    public Cursor getAllData() {
        // Open the db for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;

        // Cursor represents the result of a query
        res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
