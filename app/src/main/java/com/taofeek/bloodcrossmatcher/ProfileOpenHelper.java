package com.taofeek.bloodcrossmatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProfileOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bloodmatcherprofile.db";
    public static final int DATABASE_VERSION = 5;
    private final Context context;

    public ProfileOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase mydb = this.getWritableDatabase();
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Profile.SQL_CREATE_TABLE);
        //preLoadedData();

    }
     public void insertData(String name, String email , String number , String age , String blood_type,
                            String status , String gender ){
         ProfileOpenHelper mdb = new ProfileOpenHelper(context);

         SQLiteDatabase db = mdb.getReadableDatabase();
         ContentValues values = new ContentValues();
         values.put(DatabaseContract.Profile.COLUMN_EMAIL, email);
         values.put(DatabaseContract.Profile.COLUMN_NAME, name);
         values.put(DatabaseContract.Profile.COLUMN_NUMBER, number);
         values.put(DatabaseContract.Profile.COLUMN_AGE, age);
         values.put(DatabaseContract.Profile.COLUMN_BLOOD_TYPE, blood_type);
         values.put(DatabaseContract.Profile.COLUMN_STATUS, status);
         values.put(DatabaseContract.Profile.COLUMN_GENDER, gender);
         long newRowId = db.insert(DatabaseContract.Profile.TABLE_NAME, null, values);

     }
     public void preLoadedData (){
        insertData("Taofeek","oduola.taofeekkkola@gmail.com","08099285509" ,
                "21" , "A+" , "donor" ,"male");
        insertData("Aminat","oduola.aminat@gmail.com","08088254754" ,
                "26" , "O+" , "donor" ,"female");
        insertData("Bisola","oduola.bisola@gmail.com","08093461520" ,
                "28" , "B+" , "donor" ,"female");
        insertData("Damilare","oduola.damilare@gmail.com","08167999660" ,
                "30" , "AB+" , "donor" ,"male");
        insertData("Omotayo","oduola.omatayo@gmail.com","08023901075" ,
                "31" , "A-" , "donor" ,"female");
        insertData("Felix","balogun.felix@gmail.com","08026701075" ,
                "25" , "O-" , "donor" ,"male");
        insertData("Emmanuel","balogun.emmanuel@gmail.com","08026703475" ,
                "29" , "B-" , "donor" ,"male");
        insertData("Sarah","balogun.sarah@gmail.com","08026703475" ,
                "23" , "AB-" , "donor" ,"female");

     }
     public void donorGetter(String selection, String[] selectionArgs){

        String[] projection = {DatabaseContract.Profile.COLUMN_NAME,
                                DatabaseContract.Profile.COLUMN_EMAIL,
                                DatabaseContract.Profile.COLUMN_BLOOD_TYPE};

         ProfileOpenHelper mdb = new ProfileOpenHelper(context);
         SQLiteDatabase db = mdb.getReadableDatabase();
         Cursor cursor = db.query(
                 DatabaseContract.Profile.TABLE_NAME,   // The table to query
                 projection,             // The array of columns to return (pass null to get all)
                 selection,              // The columns for the WHERE clause
                 selectionArgs,          // The values for the WHERE clause
                 null,                   // don't group the rows
                 null,                   // don't filter by row groups
                 null               // The sort order
         );
         boolean more = cursor.moveToFirst();
         while(more) {
             RecyclerAdapter adaptercursor = new RecyclerAdapter(context,cursor);
             more = cursor.moveToNext();
         }



     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
