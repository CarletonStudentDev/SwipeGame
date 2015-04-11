package com.example.owner.gameproject;

import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Displays text numbers onto the screen using a minimum of 2 digits.
 *
 * This class is a subclass of TextObject.
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2015-03-27
 */

public class ClockTextObject extends TextObject
{

    /**
     * textSize float value representing the text size to be displayed on screen.
     *
     */

    private float textSize;


    /**
     * paint Paint instance representing the color of the text being displayed on screen.
     */

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


    int count = 0;

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

        if (text.equals("01") || text.equals("02") || text.equals("03"))
        {
            paint.setColor(ColorsLoader.loadColorByName("emergency red"));
            paint.setTextSize(this.textSize + (count*2/1080f) *GameView.WIDTH);
            if(count == 20){
                count = 0;
            }
            this.setPaint(paint);
            count++;
        }


        else if (text.equals("04") || text.equals("05"))
        {
            paint.setColor(ColorsLoader.loadColorByName("dark yellow"));
            paint.setTextSize(this.textSize + (count/1080f) *GameView.WIDTH);
            if(count == 20){
                count = 0;
            }
            this.setPaint(paint);
            count++;
        }

        else
            this.setPaint(this.paint);


        super.setText(text);
    }
}
