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
    public static final class Profile implements BaseColumns {
        public static final String TABLE_NAME = "profile";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BLOOD_TYPE = "blood_type";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_NUMBER = "number";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, "  +
                COLUMN_NAME + ", " +
                COLUMN_AGE + ", " +
                COLUMN_GENDER + ", " +
                COLUMN_BLOOD_TYPE + " TEXT NOT NULL, " +
                COLUMN_STATUS + ", " +
                COLUMN_NUMBER  + " )" ;



    }
}
