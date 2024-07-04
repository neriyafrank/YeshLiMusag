package com.example.yeshlimusag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public static final String TEXTS_TABLE = "TEXTS_TABLE";
    public static final String TEXT_TOPIC = "TEXT_TOPIC";
    public static final String TEXT_TITLE = "TEXT_TITLE";
    public static final String TEXT_WORDS = "TEXT_WORDS";
    public static final String TEXTS_ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "texts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TEXTS_TABLE + " (" + TEXTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TEXT_TOPIC + ", " + TEXT_TITLE + ", " + TEXT_WORDS + ")";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
