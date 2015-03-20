package com.example.owner.gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
    private Bitmap bitmap;

    private TextObject numLives;

    private Paint paint;

    private Matrix matrix;

    public Lives(String text, Typeface typeface, int colorText)
    {
        bitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.fullheart);

        numLives = new TextObject(text, 800f, 100f, typeface, colorText, 100f);
    }


    public void setText(String text)
    {
        numLives.setText(text);
    }


    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, matrix, paint);
        numLives.draw(canvas);
    }



}
