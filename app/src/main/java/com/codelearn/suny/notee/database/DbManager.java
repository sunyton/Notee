package com.codelearn.suny.notee.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codelearn.suny.notee.BookInfo;
import com.codelearn.suny.notee.NoteInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suny on 2017/5/21.
 */

public class DbManager {

    private SQLiteOpenHelper helper;
    public DbManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void add(String name, String title, String body, String date) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.BOOK_NAME, name);
        values.put(Constants.TITLE, title);
        values.put(Constants.BODY, body);
        values.put(Constants.DATE, date);
        db.insert(Constants.TABLE_NAME, null, values);
        db.close();
    }
    public void add_book(String name, int img) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.BOOK_NAME, name);
        values.put(Constants.BOOK_IMG, img);
        db.insert("book", null, values);
        db.close();
    }



    public List<NoteInfo> query(String bookName) {
        List<NoteInfo> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true, Constants.TABLE_NAME, new String[]{Constants.TITLE, Constants.BODY, Constants.DATE}, "bookname=?", new String[]{bookName}, null, null, "date desc", "");
        while (cursor.moveToNext()) {
            String titleName = cursor.getString(cursor.getColumnIndex(Constants.TITLE));
            String body = cursor.getString(cursor.getColumnIndex(Constants.BODY));
            String date = cursor.getColumnName(cursor.getColumnIndex(Constants.DATE));
            NoteInfo noteInfo = new NoteInfo();
            noteInfo.setNoteTitle(titleName);
            noteInfo.setNoteBody(body);
            noteInfo.setNoteDate(date);
            list.add(noteInfo);

        }
        cursor.close();
        db.close();

        return list;
    }



}
