package com.taofeek.bloodcrossmatcher;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract(){}
    public static final class Authentication implements BaseColumns {
        public static final String TABLE_NAME = "authentication";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";

        //CREATE TABLE course_info (course_id , course title);
        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, "  +
                COLUMN_PASSWORD + " TEXT NOT NULL " + ")" ;


    }
}
