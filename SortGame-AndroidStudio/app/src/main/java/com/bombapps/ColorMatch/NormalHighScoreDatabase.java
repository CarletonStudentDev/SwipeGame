package com.bombapps.ColorMatch;

/**
 * Created by erickadbay on 15-04-06.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NormalHighScoreDatabase extends SQLiteOpenHelper {
    public NormalHighScoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_NORMAL_SCORES = "normalScores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE_NORMAL = "normalScore";

    private static final String DATABASE_NAME = "normalScores.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NORMAL_SCORES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SCORE_NORMAL
            + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NormalHighScoreDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NORMAL_SCORES);
        onCreate(db);
    }

}
