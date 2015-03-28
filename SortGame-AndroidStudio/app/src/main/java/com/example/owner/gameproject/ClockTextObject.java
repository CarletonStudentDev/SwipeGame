package com.example.owner.gameproject;

import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Displays text numbers onto the screen using a minimum of 2 digits.
 *
 * This class is a subclass of TextObject.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2015-03-27
 */
public class ClockTextObject extends TextObject
{

    private float textSize;

    private Paint paint;

    /**
     * Constructor for the TextObject class.
     *
     * @param text String object representing the text to be displayed on screen.
     * @param xCoordinate float value representing the x-coordinate location of text in pixels.
     * @param yCoordinate float value representing the y-coordinate location of text in pixels.
     * @param typeface Typeface object representing the font type of the text.
     * @param color integer value obtained from ColorLoader representing the color of the text.
     * @param textSize float value representing the size of the text in pixels.
     */

    public ClockTextObject(String text, float xCoordinate, float yCoordinate, Typeface typeface, int color, float textSize)
    {
        super(text, xCoordinate, yCoordinate, typeface, color, textSize);
        this.textSize = textSize;
        this.paint = new Paint(this.getPaint());
    }

    /**
     * Setter for the text.
     *
     * @param text String object representing the text to be displayed on screen.
     */

    @Override
    public void setText(String text)
    {
        Paint paint = new Paint(this.getPaint());

        if (text.length() < 2)
           text = "0" + text;

        if (text.equals("03"))
        {
            paint.setColor(ColorsLoader.loadColorByName("dark green"));
            paint.setTextSize(this.textSize + (20f/1080) *GameView.WIDTH);

            this.setPaint(paint);
        }

        else if (text.equals("02"))
        {
            paint.setColor(ColorsLoader.loadColorByName("dark yellow"));
            paint.setTextSize(this.textSize + (20f/1080) *GameView.WIDTH);

            this.setPaint(paint);
        }

        else if (text.equals("01"))
        {
            paint.setColor(ColorsLoader.loadColorByName("darkest red"));
            paint.setTextSize(this.textSize + (20f/1080) *GameView.WIDTH);

            this.setPaint(paint);
        }

        else
            this.setPaint(this.paint);


        super.setText(text);
    }
}
