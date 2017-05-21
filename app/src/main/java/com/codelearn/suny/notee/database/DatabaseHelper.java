package com.codelearn.suny.notee.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codelearn.suny.notee.R;

/**
 * Created by suny on 2017/5/21.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table "
                + Constants.TABLE_NAME
                + "("+ Constants.BOOK_IMG + " varchar,"
                + Constants.BOOK_NAME + " varchar,"
                + Constants.TITLE + " varchar,"
                + Constants.BODY + " varchar,"
                + Constants.DATE + " integer)";
        String sql2 = "create table book(bookimg integer,bookname varchar)";
        db.execSQL(sql);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
