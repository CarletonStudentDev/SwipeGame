package com.example.owner.gameproject;

/**
 * Created by erickadbay on 15-04-06.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ScoreDataSource {
    // Database fields
    public static SQLiteDatabase normalDatabase, stroopDatabase, endlessDatabase; //changed to global
    private NormalHighScoreDatabase normalDbHelper;
    private StroopHighScoreDatabase stroopDbHelper;
    private EndlessHighScoreDatabase endlessDbHelper;

    public static String[] allColumnsNormal = { NormalHighScoreDatabase.COLUMN_ID, NormalHighScoreDatabase.COLUMN_SCORE_NORMAL};
    public static String[] allColumnsStroop = { StroopHighScoreDatabase.COLUMN_ID, StroopHighScoreDatabase.COLUMN_SCORE_STROOP};
    public static String[] allColumnsEndless = { EndlessHighScoreDatabase.COLUMN_ID, EndlessHighScoreDatabase.COLUMN_SCORE_ENDLESS};

    public static int normalHighScore = 0, stroopHighScore=0, endlessHighScore = 0;//changed to global

    public ScoreDataSource(Context context) {
        normalDbHelper = new NormalHighScoreDatabase(context);
        stroopDbHelper = new StroopHighScoreDatabase(context);
        endlessDbHelper = new EndlessHighScoreDatabase(context);
    }

    public void open() throws SQLException {
        normalDatabase= normalDbHelper.getWritableDatabase();
        stroopDatabase = stroopDbHelper.getWritableDatabase();
        endlessDatabase = endlessDbHelper.getWritableDatabase();
    }

    public void close() {
        normalDbHelper.close();
        stroopDbHelper.close();
        endlessDatabase.close();
    }

    public static ScoreModel createNormalScore(int score) {
        ContentValues values = new ContentValues();
        values.put(NormalHighScoreDatabase.COLUMN_SCORE_NORMAL, score);
        long insertId = normalDatabase.insert(NormalHighScoreDatabase.TABLE_NORMAL_SCORES, null,
                values);
        Cursor cursor = normalDatabase.query(NormalHighScoreDatabase.TABLE_NORMAL_SCORES,
                allColumnsNormal, NormalHighScoreDatabase.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ScoreModel newScoreModel = cursorToScoreModel(cursor);
        cursor.close();
        return newScoreModel;
    }

    public static ScoreModel createStroopScore(int score) {
        ContentValues values = new ContentValues();
        values.put(StroopHighScoreDatabase.COLUMN_SCORE_STROOP, score);
        long insertId = stroopDatabase.insert(StroopHighScoreDatabase.TABLE_STROOP_SCORES, null,
                values);
        Cursor cursor = stroopDatabase.query(StroopHighScoreDatabase.TABLE_STROOP_SCORES,
                allColumnsStroop, StroopHighScoreDatabase.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ScoreModel newScoreModel = cursorToScoreModel(cursor);
        cursor.close();
        return newScoreModel;
    }

    public static ScoreModel createEndlessScore(int score) {
        ContentValues values = new ContentValues();
        values.put(EndlessHighScoreDatabase.COLUMN_SCORE_ENDLESS, score);
        long insertId = endlessDatabase.insert(EndlessHighScoreDatabase.TABLE_ENDLESS_SCORES, null,
                values);
        Cursor cursor = endlessDatabase.query(EndlessHighScoreDatabase.TABLE_ENDLESS_SCORES,
                allColumnsEndless, EndlessHighScoreDatabase.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ScoreModel newScoreModel = cursorToScoreModel(cursor);
        cursor.close();
        return newScoreModel;
    }

    /*
    public void deleteScore(ScoreModel score) {
        long id = score.getId();
        System.out.println("Score deleted with id: " + id);
        database.delete(ScoreDatabase.TABLE_SCORES, ScoreDatabase.COLUMN_ID
                + " = " + id, null);
    }

    public List<ScoreModel> getAllScores() {
        List<ScoreModel> scores = new ArrayList<>();

        Cursor cursor = database.query(ScoreDatabase.TABLE_SCORES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ScoreModel score = cursorToScoreModel(cursor);
            scores.add(score);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return scores;
    }

    */
    public static ScoreModel cursorToScoreModel(Cursor cursor) {
        ScoreModel comment = new ScoreModel();
        comment.setId(cursor.getLong(0));
        comment.setScore(cursor.getInt(1));
        return comment;
    }

    public static int getNormalHighScore(){
        int value;
        Cursor cur;
        cur = normalDatabase.rawQuery( "select * from "+NormalHighScoreDatabase.TABLE_NORMAL_SCORES, null );
        //cur = normalDatabase.rawQuery( "select "+ NormalHighScoreDatabase.COLUMN_SCORE_NORMAL+" from "+ NormalHighScoreDatabase.TABLE_NORMAL_SCORES, null );

        for (int i = 0; i < cur.getCount(); i++) {
            if(!cur.moveToPosition(i)){
                break;
            }
            value = cur.getInt(1);

            if (value>normalHighScore){
                normalHighScore=value;
            }


        }
        cur.close();
        return normalHighScore;

        //database.execSQL("DELETE FROM " + ScoreDatabase.TABLE_COMMENTS +"");
        //return 0;

    }

    public static int getStroopHighScore(){
        int value;
        Cursor cur;
        //cur = stroopDatabase.rawQuery( "select "+ StroopHighScoreDatabase.COLUMN_SCORE_STROOP+" from "+ NormalHighScoreDatabase.TABLE_NORMAL_SCORES, null );
        cur = stroopDatabase.rawQuery( "select * from "+StroopHighScoreDatabase.TABLE_STROOP_SCORES, null );

        for (int i = 0; i < cur.getCount(); i++) {
            if(!cur.moveToPosition(i)){
                break;
            }
            value = cur.getInt(1);

            if (value>stroopHighScore){
                stroopHighScore=value;
            }


        }
        cur.close();
        return stroopHighScore;

        //database.execSQL("DELETE FROM " + ScoreDatabase.TABLE_COMMENTS +"");
        //return 0;

    }

    public static int getEndlessHighScore(){
        int value;
        Cursor cur;
        //cur = endlessDatabase.rawQuery( "select "+ EndlessHighScoreDatabase.COLUMN_SCORE_ENDLESS+" from "+ NormalHighScoreDatabase.TABLE_NORMAL_SCORES, null );
        cur = endlessDatabase.rawQuery( "select * from "+EndlessHighScoreDatabase.TABLE_ENDLESS_SCORES, null );
        for (int i = 0; i < cur.getCount(); i++) {
            if(!cur.moveToPosition(i)){
                break;
            }
            value = cur.getInt(1);

            if (value>endlessHighScore){
                endlessHighScore=value;
            }


        }
        cur.close();
        return endlessHighScore;

        //database.execSQL("DELETE FROM " + ScoreDatabase.TABLE_COMMENTS +"");
        //return 0;

    }


}
