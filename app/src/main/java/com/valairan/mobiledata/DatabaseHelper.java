package com.valairan.mobiledata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "recents.db";
    final static int DB_VERSION = 1;

    public DatabaseHelper(Context mContext){
        super(mContext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        RecentsTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        RecentsTable.onUpdate(sqLiteDatabase, oldVersion, newVersion);
    }
}
