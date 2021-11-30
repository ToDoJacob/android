package com.example.mymemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "hr.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) { super(context, NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate 호출됨");
        String sql = "create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
