package com.valairan.mobiledata;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RecentsTable {
    final static String TABLE_NAME = "recents";
    final static String COLUMN_ID= "id";
    final static String COLUMN_SOURCELANG = "sourceLanguage";
    final static String COLUMN_TARGETLANG = "targetLanguage";
    final static String COLUMN_SOURCEPHRASE = "sourcePhrase";
    final static String COLUMN_TARGETPHRASE = "targetPhrase";


    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        //CREATE TABLE recents(id integer primary key autoincrement, sourceLanguage text not null, targetLanguage text not null, sourcePhrase text not null, targetPhrase text not null)
        sb.append("CREATE TABLE "+ RecentsTable.TABLE_NAME+ "(" + RecentsTable.COLUMN_ID + " integer primary key autoincrement," + RecentsTable.COLUMN_SOURCELANG + " text not null,"+ RecentsTable.COLUMN_TARGETLANG + " text not null,"+ RecentsTable.COLUMN_SOURCEPHRASE + " text not null,"+ RecentsTable.COLUMN_TARGETPHRASE + " text not null);");

        try {
            sqLiteDatabase.execSQL(sb.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void onUpdate(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){

    }
}
