package com.jezik.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 29.06.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper mDbHelper;

    private static final String TAG = "DbHelper";

    //Database Info
    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    //Create statement
    private static final String CREATE_USERDETAIL_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_USERdETAIL +
            "(" +
            UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
            UserContract.UserEntry.USER_ID + " TEXT," +
            UserContract.UserEntry.NAME + " TEXT," +
            UserContract.UserEntry.COLLEGE + " TEXT," +
            UserContract.UserEntry.PLACE + " TEXT," +
            UserContract.UserEntry.NUMBER + " TEXT" +
            ")";

    //Delete statement
    private static final String DELETE_USERDETAIL_TABLE = "DROP TABLE IF EXIST " + UserContract.UserEntry.TABLE_USERdETAIL;

    //Get all data from table
    private static final String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + UserContract.UserEntry.TABLE_USERdETAIL;

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DbHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.

        if (mDbHelper == null) {
            mDbHelper = new DbHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERDETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DELETE_USERDETAIL_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    /*
   Insert a  user detail into database
   */
    public void insertUserDetail(UserData userData) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(UserContract.UserEntry.NAME, userData.name);
            values.put(UserContract.UserEntry.COLLEGE, userData.college);
            values.put(UserContract.UserEntry.PLACE, userData.place);
            values.put(UserContract.UserEntry.USER_ID, userData.user_id);
            values.put(UserContract.UserEntry.NUMBER, userData.number);

            db.insertOrThrow(UserContract.UserEntry.TABLE_USERdETAIL, null, values);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    /*
   Fetch all data from UserTable
    */
    public List<UserData> getAllUser() {

        List<UserData> userdetail = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    UserData userData = new UserData();
                    userData.name = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.NAME));
                    userData.college = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLLEGE));
                    userData.place = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PLACE));
                    userData.user_id = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.USER_ID));
                    userData.number = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.NUMBER));

                    userdetail.add(userData);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return userdetail;
    }

    /*
   Delete single row from UserTable
     */
    public void deleteRow(String name) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();
            db.execSQL("DELETE FROM " + UserContract.UserEntry.TABLE_USERdETAIL + " WHERE name ='" + name + "'");
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to delete  users detail");
        } finally {
            db.endTransaction();
        }
    }
}
