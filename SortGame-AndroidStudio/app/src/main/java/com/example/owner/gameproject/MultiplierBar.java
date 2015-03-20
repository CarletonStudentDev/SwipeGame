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

    private static final int BACKGROUNDALPHA = 50,
                             BLOCKNUM = 4;

    private static final float BLOCKSIZE = 175f,
                               SPACEBETWEENBLOCKS = 5f,
                               BACKGROUNDMULTIPLIER = (BLOCKSIZE + SPACEBETWEENBLOCKS) * BLOCKNUM - SPACEBETWEENBLOCKS;


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
     * backgroundPaint Paint instance representing the color of the multiplier bar.
     */

    private Paint backgroundPaint,
                  currentMultiPaint,
                  multiplierCirclePaint;


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
        this.multiplierTextObject = new TextObject("x" + multiplierNum, 20f +  BACKGROUNDMULTIPLIER + 100f , 20f, typeface, textColor, 20f);
        this.multiplierBarNum = multiplierBarNum;

        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(ColorsLoader.loadColorByName("light gray"));
        this.backgroundPaint.setAlpha(BACKGROUNDALPHA);

        this.currentMultiPaint = new Paint();
        this.currentMultiPaint.setColor(ColorsLoader.loadColorByInt(this.findCorrespondingColor()));;

        this.multiplierCirclePaint = new Paint();
        this.multiplierCirclePaint.setColor(ColorsLoader.loadColorByName("black"));
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
        this.currentMultiPaint.setColor(ColorsLoader.loadColorByInt(this.findCorrespondingColor()));
    }


    private int findCorrespondingColor()
    {
        int value = (int) (Math.log10((double)this.multiplier) / Math.log10((double)2)) + 1;
        return value;
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {
        canvas.drawRect(20f, 20f, 20f +  BACKGROUNDMULTIPLIER, 150f, this.backgroundPaint);

        for(int i= 0; i < this.multiplierBarNum ; i++)
        {
            float left = 20f + (SPACEBETWEENBLOCKS + BLOCKSIZE) * i;
            float right = left + BLOCKSIZE;
            canvas.drawRect(left, 20f, right, 150f, this.currentMultiPaint);
        }



        if (this.multiplier > 1)
        {
            this.multiplierTextObject.setText("x" + this.multiplier);
            canvas.drawCircle(20f +  BACKGROUNDMULTIPLIER + 100f, 20f, 100f, this.multiplierCirclePaint);
            this.multiplierTextObject.draw(canvas);
        }


    }
}
