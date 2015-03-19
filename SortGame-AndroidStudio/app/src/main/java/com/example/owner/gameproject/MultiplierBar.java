package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * MultiplierBar class contains all the information required to display
 * the multiplier bar on the screen.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2015-03-19
 *
 */

public class MultiplierBar
{

    /**
     * multiplierTextObject TextObject instance representing the text to be displayed on screen.
     */

    private TextObject multiplierTextObject;

    /**
     * multiplierBarNum integer value representing the amount of correct answers.
     * multiplier integer value representing the multiplier number for multiplying the score.
     */

    private int multiplierBarNum,
                multiplier;

    /**
     * paint Paint instance representing the color, and font of the TextObject.
     */

    private Paint paint;


    /**
     * Constructor for the MultiplierBar class.
     *
     * @param multiplierNum integer value representing the multiplier number for multiplying the score.
     * @param multiplierBarNum integer value representing the amount of correct answers.
     * @param typeface Typeface object representing the font type of the text
     * @param textColor integer value obtained from getResources() representing the color of the text.
     */

    public MultiplierBar(int multiplierNum, int multiplierBarNum, Typeface typeface, int textColor)
    {
        this.multiplier = multiplierNum;
        this.multiplierTextObject = new TextObject("x" + multiplierNum, 1000f, 100f, typeface, textColor, 20f);
        this.multiplierBarNum = multiplierBarNum;

        this.paint = new Paint();
    }


    /**
     * Setter for the multiplier number and the multiplier bar number.
     *
     * @param multiplierNum integer value representing the multiplier number for multiplying the score.
     * @param multiplierBarNum integer value representing the amount of correct answers for that
     *                         particular multiplier number.
     */

    public void setMultiplierValues(int multiplierNum, int multiplierBarNum)
    {
        this.multiplier = multiplierNum;
        this.multiplierBarNum = multiplierBarNum;
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {
        /*canvas.drawRect();

        for (int i = 0; i < this.multiplierBarNum; i++)
            canvas.drawRect();

        if (this.multiplier > 1)
        {
            this.multiplierTextObject.setText("x" + this.multiplier);
            canvas.drawCircle();
            canvas.drawText();
        }
        */

    }
}
