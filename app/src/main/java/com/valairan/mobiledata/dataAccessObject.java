package com.valairan.mobiledata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class dataAccessObject {
    private SQLiteDatabase db;

    public dataAccessObject(SQLiteDatabase db) {
        this.db = db;
    }

    public void save (RecentTranslation recentTranslation){
        ContentValues values = new ContentValues();
        values.put(RecentsTable.COLUMN_SOURCELANG, recentTranslation.getSourceLang());
        values.put(RecentsTable.COLUMN_TARGETLANG, recentTranslation.getTargetLang());
        values.put(RecentsTable.COLUMN_SOURCEPHRASE, recentTranslation.getSourcePhrase());
        values.put(RecentsTable.COLUMN_TARGETPHRASE, recentTranslation.getTargetPhrase());
        db.insert(RecentsTable.TABLE_NAME, null, values);

    }

    public ArrayList<RecentTranslation> getAll(){
        ArrayList<RecentTranslation> recents =  new ArrayList<>();
        Cursor cursor = db.query(RecentsTable.TABLE_NAME,
                new String[]{RecentsTable.COLUMN_ID,
                        RecentsTable.COLUMN_SOURCELANG,
                        RecentsTable.COLUMN_TARGETLANG,
                        RecentsTable.COLUMN_SOURCEPHRASE,
                        RecentsTable.COLUMN_TARGETPHRASE },
                null, null, null, null, null);

        while(cursor.moveToNext()){
            RecentTranslation temp = buildEntryFromCursor(cursor);
            recents.add(temp);
        }
        return recents;
    }

    private RecentTranslation buildEntryFromCursor(Cursor cursor) {
        RecentTranslation temp = new RecentTranslation();
        temp.setId(cursor.getLong(0));
        temp.setSourceLang(cursor.getString(1));
        temp.setTargetLang(cursor.getString(2));
        temp.setSourcePhrase(cursor.getString(3));
        temp.setTargetPhrase(cursor.getString(4));
        return temp;
    }
}
