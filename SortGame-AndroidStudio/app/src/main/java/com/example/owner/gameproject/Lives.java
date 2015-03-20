package com.example.owner.gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;

/**
 * Displays the number of hearts onto the screen.
 *
 * @author Joel Prakash
 * @version 1.0
 * @since 2015-03-20
 */
public class Lives
{
    /**
     * bitmap Bitmap instance representing the hearts bitmap loaded from Resources.
     */

    private Bitmap bitmap;


    /**
     * numLives TextObject instance representing the number of lives present in the game.
     */

    private TextObject numLives;


    /**
     * Constructor for the Lives class.
     *
     * @param text String object representing the number of lives present in the game.
     * @param typeface Typeface object representing the font type of the text.
     * @param colorText integer value obtained from ColorLoader representing the color of the text.
     */

    public Lives(String text, Typeface typeface, int colorText)
    {
        bitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.fullheart);
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

        numLives = new TextObject(text, 800f, 100f, typeface, colorText, 100f);
    }


    /**
     * Setter for the number of lives present in the game.
     *
     * @param text String object representing the number of lives present in the game.
     */
    public void setText(String text)
    {
        numLives.setText(text);
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, 600, 800, null);
        numLives.draw(canvas);
    }



}
