package com.bombapps.ColorMatch;

/**
 * Created by erickadbay on 15-04-06.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StroopHighScoreDatabase extends SQLiteOpenHelper {

    public StroopHighScoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_STROOP_SCORES = "stroopScores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE_STROOP = "stroopScore";

    private static final String DATABASE_NAME = "stroopScores.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_STROOP_SCORES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SCORE_STROOP
            + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(StroopHighScoreDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STROOP_SCORES);
        onCreate(db);
    }
}
