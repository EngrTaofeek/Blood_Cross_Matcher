package com.taofeek.bloodcrossmatcher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.AccessControlContext;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BloodMatcher.db";
    public static final int DATABASE_VERSION = 1;
    private final Context context;
    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = null;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Authentication.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
