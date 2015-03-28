package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * MultiplierBar class contains all the information required to display
 * the multiplier bar on the screen.
 *
 * @author Jeton Sinoimeri
 * @version 1.2
 * @since 2015-03-19
 *
 */

public class MultiplierBar
{

    /**
     * BACKGROUNDALPHA integer constant representing the transparency of the background multiplier
     * BLOCKNUM integer constant representing the number of blocks to fill before multiplier increased.
     */

    private static final int BACKGROUNDALPHA = 50,
                             BLOCKNUM = 5;


    /**
     * BLOCKSIZE float constant representing the size of the blocks in pixels.
     * SPACEBETWEENBLOCKS float constant representing the size of the space between each block in pixels.
     * BACKGROUNDMULTIPLIER float constant representing the size of the background multiplier.
     *
     * LEFTCOORDINATE float constant representing the left coordinate of the multiplier bar in pixels.
     * RIGHTCOORDINATE float constant representing the right coordinate of the multiplier bar in pixels.
     * TOPCOORDINATE float constant representing the top coordinate of the multiplier bar in pixels.
     *
     * BOTTOMCOORDINATE float constant representing the bottom coordinate of the multiplier bar in pixels.
     * LEFTCIRCLECOORDINATE float constant representing the left coordinate of the circle in pixels.
     * TOPCIRCLECOORDINATE float constant representing the top coordinate of the circle in pixels.
     *
     * RADIUS float constant representing the radius of the circle in pixels.
     * LEFTTEXTCOORDINATE_1 float constant representing the left coordinate of the text of length 2.
     * TOPTEXTCOORDINATE float constant representing the top coordinate of the text of any length.
     */

    private static final float BLOCKSIZE = (175f/1080) * GameView.WIDTH,
                               SPACEBETWEENBLOCKS = (5f/1080) * GameView.WIDTH,
                               BACKGROUNDMULTIPLIER = (BLOCKSIZE + SPACEBETWEENBLOCKS) * BLOCKNUM - SPACEBETWEENBLOCKS,
                               LEFTCOORDINATE = 0f,
                               RIGHTCOORDINATE = LEFTCOORDINATE + BACKGROUNDMULTIPLIER,
                               TOPCOORDINATE = (220f/1701) * GameView.HEIGHT,
                               BOTTOMCOORDINATE = TOPCOORDINATE + (90f/1701) * GameView.HEIGHT,
                               LEFTCIRCLECOORDINATE = RIGHTCOORDINATE + (95f/1080) * GameView.WIDTH,
                               TOPCIRCLECOORDINATE = TOPCOORDINATE + (50f/1701) * GameView.HEIGHT,
                               RADIUS = (90f/1080) * GameView.WIDTH,
                               LEFTTEXTCOORDINATE_1 = LEFTCIRCLECOORDINATE ,
                               TOPTEXTCOORDINATE = TOPCIRCLECOORDINATE + (20f/1701) * GameView.HEIGHT;



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
     * backgroundPaint Paint instance representing the color of the background multiplier bar.
     * currentMutliPaint Paint instance representing the color of the current multiplier.
     * multiplierCirclePaint Paint instance representing the color of the multiplier circle to show
     *                       the multiplier text.
     */

    private Paint backgroundPaint,
                  currentMultiPaint,
                  multiplierCirclePaint,
                  prevMultiPaint;


    /**
     * Constructor for the MultiplierBar class.
     *
     * @param multiplierNum integer value representing the multiplier number for multiplying the score.
     * @param multiplierBarNum integer value representing the amount of correct answers.
     * @param typeface Typeface object representing the font type of the text
     * @param textColor integer value obtained from ColorLoader representing the color of the text.
     */

    public MultiplierBar(int multiplierNum, int multiplierBarNum, Typeface typeface, int textColor)
    {
        this.multiplier = multiplierNum;
        this.multiplierTextObject = new TextObject("x" + multiplierNum, LEFTTEXTCOORDINATE_1,
                                                   TOPTEXTCOORDINATE, typeface, textColor, (100f/1080) * GameView.WIDTH);

        this.multiplierBarNum = multiplierBarNum;

        this.backgroundPaint = new Paint();

        this.currentMultiPaint = new Paint();

        this.currentMultiPaint.setColor(ColorsLoader.loadColorByInt(this.findCorrespondingColor()));

        this.prevMultiPaint = new Paint();


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

        if (this.multiplier > 8)
            this.currentMultiPaint.setColor(this.prevMultiPaint.getColor());

        if (this.multiplier > 1)
            this.prevMultiPaint.setColor(ColorsLoader.loadColorByInt(this.findCorrespondingColor() - 1));

    }


    /**
     * Finds the corresponding multiplier bar color.
     *
     * @return integer value representing the color of the multiplier bar color.
     */

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

        canvas.drawRect(LEFTCOORDINATE, TOPCOORDINATE, RIGHTCOORDINATE, BOTTOMCOORDINATE, this.backgroundPaint);



        // draws the black circle and multiplier text object
        if (this.multiplier > 1)
        {
            // draws the bars with spaces in between
            for(int i= 0; i < BLOCKNUM; i++)
            {
                float left = LEFTCOORDINATE + (SPACEBETWEENBLOCKS + BLOCKSIZE) * i;
                float right = left + BLOCKSIZE;
                canvas.drawRect(left, TOPCOORDINATE, right, BOTTOMCOORDINATE, this.prevMultiPaint);
            }


            this.multiplierTextObject.setText("x" + this.multiplier);

            canvas.drawCircle(LEFTCIRCLECOORDINATE, TOPCIRCLECOORDINATE, RADIUS, this.multiplierCirclePaint);
            this.multiplierTextObject.draw(canvas);
        }


        // draws the bars with spaces in between
        for(int i= 0; i < this.multiplierBarNum; i++)
        {
            float left = LEFTCOORDINATE + (SPACEBETWEENBLOCKS + BLOCKSIZE) * i;
            float right = left + BLOCKSIZE;
            canvas.drawRect(left, TOPCOORDINATE, right, BOTTOMCOORDINATE, this.currentMultiPaint);
        }



    }
}
