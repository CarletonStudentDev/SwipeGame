package com.example.owner.gameproject;

import android.graphics.Paint;

/**
 * GameBoard class creates the board of the game containing
 * four different colored circles placing them at each corner
 * of the screen. Each circle represents the corresponding
 * discarded colored card.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2015-03-08
 *
 */

public class GameBoard
{

    /**
     * RADIUS: constant float value representing the radius of the circle.
     *
     */

    private static final float RADIUS = 200.0f;


    /**
     * redCircleColor: android.graphics.Paint instance representing color red.
     * blueCircleColor: android.graphics.Paint instance representing color blue.
     * greenCircleColor: android.graphics.Paint instance representing color green.
     * purpleCircleColor: android.graphics.Paint instance representing color purple.
     *
     */

    private Paint redCircleColor,
                  blueCircleColor,
                  greenCircleColor,
                  purpleCircleColor;


    /**
     * Constructor for the GameBoard class.
     *
     */

    public GameBoard()
    {
        this.redCircleColor = new Paint();
        this.redCircleColor.setColor(GameView.instance.getResources().getColor(R.color.red));

        this.blueCircleColor = new Paint();
        this.blueCircleColor.setColor(GameView.instance.getResources().getColor(R.color.blue));

        this.greenCircleColor = new Paint();
        this.greenCircleColor.setColor(GameView.instance.getResources().getColor(R.color.green));

        this.purpleCircleColor = new Paint();
        this.purpleCircleColor.setColor(GameView.instance.getResources().getColor(R.color.purple));

    }


    /**
     * Gets the color of the corresponding canvas quadrant.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return integer value representing the color of the corresponding color.
     *
     */

    public int getQuadrantColor(float x, float y)
    {
        if (this.redTouched(x, y))
            return R.color.red;

        else if (this.blueTouched(x, y))
            return R.color.blue;

        else if (this.greenTouched(x, y))
            return R.color.green;

        else if (this.purpleTouched(x, y))
            return R.color.purple;

        return 0;
    }


    /**
     * Checks if the color red is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color
     *         red is touched.
     *
     */

    private boolean redTouched(float x, float y)
    {
        return (x >= 0.1f) && (y >= 0.05f);
    }


    /**
     * Checks if the color green is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color
     *         green is touched.
     *
     */

    private boolean greenTouched(float x, float y)
    {
        return (x <= -0.1f) && (y >= 0.05f);
    }


    /**
     * Checks if the color blue is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color
     *         blue is touched.
     *
     */

    private boolean blueTouched(float x, float y)
    {
        return (x >= 0.1f) && (y <= -0.45f);
    }


    /**
     * Checks if the color purple is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color
     *         purple is touched.
     *
     */

    private boolean purpleTouched(float x, float y)
    {
        return (x <= -0.1f) && (y <= -0.45f);
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     *
     */

    public void draw()
    {
        // this values are temporary
        GameLoopThread.canvas.drawCircle(240.0f, 461.25f, RADIUS, this.redCircleColor);
        GameLoopThread.canvas.drawCircle(810.0f, 461.25f, RADIUS, this.greenCircleColor);

        GameLoopThread.canvas.drawCircle(810.0f, 1383.75f, RADIUS, this.blueCircleColor);
        GameLoopThread.canvas.drawCircle(240.0f, 1383.75f, RADIUS, this.purpleCircleColor);

    }
}
