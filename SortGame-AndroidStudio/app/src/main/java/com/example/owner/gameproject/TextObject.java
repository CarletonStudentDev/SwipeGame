package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * TextObject displays string object to the screen using
 * android.graphics.Canvas class.
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2015-03-14
 */

public class TextObject
{
    /**
     * text String object representing the text to be drawn onto the screen.
     *
     */

    private String text;

    /**
     * xCoordinate float value representing the x-coordinate in pixels.
     * yCoordinate float value representing the y-coordinate in pixels.
     *
     */

    private float xCoordinate,
                  yCoordinate;

    /**
     * paint Paint instance representing the color, and font of the TextObject.
     *
     */

    private Paint paint;


    /**
     * Constructor for the TextObject class.
     *
     * @param text String object representing the text to be displayed on screen.
     * @param xCoordinate float value representing the x-coordinate location of text in pixels.
     * @param yCoordinate float value representing the y-coordinate location of text in pixels.
     * @param typeface Typeface object representing the font type of the text.
     * @param color integer value obtained from getResources() representing the color of the text.
     * @param textSize float value representing the size of the text in pixels.
     */

    public TextObject(String text, float xCoordinate, float yCoordinate, Typeface typeface, int color, float textSize)
    {
        this.text = text;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        this.paint = new Paint();
        this.paint.setTypeface(typeface);
        this.paint.setColor(color);
        this.paint.setTextSize(textSize);
    }


    /**
     * Setter for the text.
     *
     * @param text String object representing the text to be displayed on screen.
     */

    public void setText(String text)
    {
        this.text = text;
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {
        canvas.drawText(this.text, this.xCoordinate, this.yCoordinate, this.paint);
    }
}
