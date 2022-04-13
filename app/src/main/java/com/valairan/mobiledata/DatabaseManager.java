package com.valairan.mobiledata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    Context mContext;
    SQLiteDatabase db;
    DatabaseHelper helper;
    dataAccessObject dao;

    public DatabaseManager(Context context){
        this.mContext = context;
        helper = new DatabaseHelper(mContext);
        db = helper.getWritableDatabase();
        dao = new dataAccessObject(db);
    }

    public dataAccessObject getDao() {
        return dao;
    }
}
