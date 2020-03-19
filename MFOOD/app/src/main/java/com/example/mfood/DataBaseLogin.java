package com.example.mfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;




class DatabaseLogin extends SQLiteOpenHelper {
    Context context;

    private final static String DATABASE_NAME="LOGIN_DATABASE.db";
    private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="LOGIN";
    private final static String COL_ID="id";
    private final static String COL_USERNAME="user_name";
    private final static String COL_PASSWORD="pass_word";

    public DatabaseLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + TABLE_NAME + " ("+
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_USERNAME +" TEXT, "+
                COL_PASSWORD +" TEXT);";

        db.execSQL(query);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void InsertData(String userName , String passWord)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_USERNAME, userName);
        contentValues.put(COL_PASSWORD, passWord);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
        {
            Toast.makeText(context, "Register Fail~", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context, "Register Successful~", Toast.LENGTH_LONG).show();
        sqLiteDatabase.close();
    }

    public Cursor readAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;

        String query = "SELECT * FROM " + TABLE_NAME;

        cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor==null)
        {
            return null;
        }
        else
        return  cursor;

    }

}
