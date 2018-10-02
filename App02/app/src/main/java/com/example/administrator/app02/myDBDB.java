package com.example.administrator.app02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBDB extends SQLiteOpenHelper {
    public myDBDB(Context context) {
        super(context, "needle", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_NEEDLE(_id Integer primary key autoincrement, needleTime TEXT, needleName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_NEEDLE");
        onCreate(db);
    }
}