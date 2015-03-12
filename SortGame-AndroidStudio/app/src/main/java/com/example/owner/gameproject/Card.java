package com.example.owner.gameproject;

import android.graphics.Paint;

import java.util.Random;


/**
 * Card represents a colored rectangular element
 * located at the center of the screen which the
 * user matches to one of the four colored circles
 * at the sides of the screen.
 *
 * @author Robert
 * @version 1.3
 * @since 2015-02-27
 *
 */

public class Card
{

    /**
     * SIZE: constant float value representing the size of the rectangle.
     *
     */

    private static final float SIZE = 1f;


    /**
     * DEFAULTNUMPATTERNATTR: integer value representing the default number
     *                        of pattern attributes for a Card.
     *
     */

    private static final int DEFAULTNUMPATTERNATTR = 5;


    /**
     * color: android.graphics.Paint instance representing the color of the card.
     *
     */

    private Paint color;


    /**
     * xCoordinate1: float value representing the left side's pixel x-coordinate of rectangle.
     * yCoordinate1: float value representing the left side's pixel y-coordinate of rectangle.
     * xCoordinate2: float value representing the right side's pixel x-coordinate of rectangle.
     * yCoordinate2: float value representing the right side's pixel y-coordinate of rectangle.
     *
     */

    private float xCoordinate1,
                  yCoordinate1,
                  xCoordinate2,
                  yCoordinate2;



    /**
     * Constructor for the Card class.
     *
     */

    public Card()
    {
        color = new Paint();

        this.generateNewColor();
    }


    /**
     * Calculates the pixel offset from the center of the
     * screen for each vertex of the square.
     *
     */

    private void pixelOffset()
    {
        float offsetFromCentre = SIZE * GameManager.scaleX;

        xCoordinate1 = GameManager.centerX - offsetFromCentre;
        yCoordinate1 = GameManager.centerY - offsetFromCentre;
        xCoordinate2 = GameManager.centerX + offsetFromCentre;
        yCoordinate2 = GameManager.centerY + offsetFromCentre;

    }


    /**
     * Gives the corresponding resource color according to the
     * color id.
     *
     * @param colorId: integer value representing the color
     *                 of the Card given by the CardGenerator.
     *
     * @return color: integer value representing the corresponding
     *                resource color determined by the colorId.
     *
     */

    private int getResourceColor(int colorId)
    {
        if (colorId == 1)
            return R.color.blue;

        else if (colorId == 2)
            return R.color.green;

        else if (colorId == 3)
            return R.color.red;

        return R.color.purple;
    }


    /**
     * Gets a random integer using random.nextInt method.
     *
     * @see java.util.Random
     *
     * @param low: integer value representing the lowest
     *             value to start generating. Inclusive.
     *
     * @param high: integer value representing the highest
     *              value to end generating. Exclusive.
     *
     * @return randomInt; integer value representing the random
     *                    integer generated by the random.nextInt
     *                    method.
     *
     */

    private int getRandValue(int low, int high)
    {
        Random random = new Random();

        int randomInt = random.nextInt(high-low) + low;

        return randomInt;
    }


    /**
     * Generates a random color and checks if the generated
     * color is the same color as the previous. If it is the
     * method keeps generating a random color until they are
     * different.
     *
     */

    public void generateNewColor()
    {
        Paint currentColor = new Paint();
        currentColor.setColor(GameView.instance.getResources()
                                               .getColor(getResourceColor(
                                                       getRandValue(1, DEFAULTNUMPATTERNATTR))));

        while (currentColor.getColor() == color.getColor())
            currentColor.setColor(GameView.instance.getResources()
                                                   .getColor(getResourceColor(
                                                           getRandValue(1, DEFAULTNUMPATTERNATTR))));

        color = currentColor;
    }


    /**
     * Getter for the color of the card.
     *
     * @return color: Paint instance representing the color of
     *                the card.
     *
     */

    public int getColorId()
    {
        return color.getColor();
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     *
     */

    public void draw()
    {
        pixelOffset();

        GameLoopThread.canvas.drawRect(xCoordinate1, yCoordinate1, xCoordinate2, yCoordinate2, color);
    }
}
