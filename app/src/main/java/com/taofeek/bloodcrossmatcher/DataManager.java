package com.taofeek.bloodcrossmatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.appcompat.app.AppCompatActivity;

import static java.security.AccessController.getContext;

public class DataManager extends AppCompatActivity {
   //public OpenHelper mdb;
   //SQLiteDatabase db;
   Context mContext;
   public void testing(){
       insertDataRegister("John@yahoo.com","heyhello");
   }
    public void insertDataRegister(String email, String password){
       // OpenHelper dbHelper = new OpenHelper();
        //mdb.onCreate(db);
        OpenHelper mdb = new OpenHelper(this);

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
        OpenHelper mdb = new OpenHelper(this);
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
                "," + DatabaseContract.Authentication.COLUMN_PASSWORD + " = ?";
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
}
