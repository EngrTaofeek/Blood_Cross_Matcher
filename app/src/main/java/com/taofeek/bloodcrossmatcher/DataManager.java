package com.taofeek.bloodcrossmatcher;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import static java.security.AccessController.getContext;

public class DataManager {
   OpenHelper mdb;
   public void testing(){
       insertDataRegister("John@yahoo.com","heyhello");
   }
    public void insertDataRegister(String email, String password){
       // OpenHelper dbHelper = new OpenHelper();
        SQLiteDatabase db = mdb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Authentication.COLUMN_EMAIL, email);
        values.put(DatabaseContract.Authentication.COLUMN_PASSWORD,password);
        long newRowId = db.insert(DatabaseContract.Authentication.TABLE_NAME, null, values);

    }
}
