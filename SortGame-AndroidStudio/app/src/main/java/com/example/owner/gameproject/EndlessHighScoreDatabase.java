package com.example.owner.gameproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erickadbay on 15-04-05.
 */
public class EndlessHighScoreDatabase extends SQLiteOpenHelper {

    public EndlessHighScoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_ENDLESS_SCORES = "endlessScores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE_ENDLESS = "endlessScore";

    private static final String DATABASE_NAME = "endlessScores.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ENDLESS_SCORES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SCORE_ENDLESS
            + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(EndlessHighScoreDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENDLESS_SCORES);
        onCreate(db);
    }
}
