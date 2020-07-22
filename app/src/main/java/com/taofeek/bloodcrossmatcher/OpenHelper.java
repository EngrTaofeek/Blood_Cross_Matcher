package com.taofeek.bloodcrossmatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.security.AccessControlContext;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bloodmatcher.db";
    public static final int DATABASE_VERSION = 3;
    private final Context context;
    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase mydb = this.getWritableDatabase();
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Authentication.SQL_CREATE_TABLE);

    }
    public void insertDataRegister(String email, String password){
        // OpenHelper dbHelper = new OpenHelper();
        //mdb.onCreate(db);
        OpenHelper mdb = new OpenHelper(context);

        SQLiteDatabase db = mdb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Authentication.COLUMN_EMAIL, email);
        values.put(DatabaseContract.Authentication.COLUMN_PASSWORD,password);
        long newRowId = db.insert(DatabaseContract.Authentication.TABLE_NAME, null, values);

    }
    public boolean loginCheck(String loginmail, String loginPassword){
        //mdb.onCreate(db);
        //mdb = new OpenHelper(this);
        //mdb.getReadableDatabase();
        OpenHelper mdb = new OpenHelper(context);
        SQLiteDatabase db = mdb.getReadableDatabase();

        //SQLiteDatabase db = mdb.getReadableDatabase();
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.Authentication.COLUMN_EMAIL,
                DatabaseContract.Authentication.COLUMN_PASSWORD

        };

// Filter results WHERE "title" = 'My Title'
        String selection =  DatabaseContract.Authentication.COLUMN_EMAIL + " = ?" +
                " AND " + DatabaseContract.Authentication.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { loginmail, loginPassword };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseContract.Authentication.COLUMN_EMAIL + " DESC";

        Cursor cursor = db.query(
                DatabaseContract.Authentication.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        int count = cursor.getCount();

        cursor.close();
        //close();

        if(count > 0){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
