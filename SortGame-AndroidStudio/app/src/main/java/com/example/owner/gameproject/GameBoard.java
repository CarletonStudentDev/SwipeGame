package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * GameBoard class creates the board of the game containing
 * four different colored circles placing them at each corner
 * of the screen. Each circle represents the corresponding
 * discarded colored card.
 *
 * @author Jeton Sinoimeri
 * @version 1.5
 * @since 2015-03-08
 */

public class GameBoard
{
    /** RADIUS constant float value representing the radius of the circle.*/
    private static final float RADIUS = GameView.WIDTH * (95.0f/720.0f);


    /**
     * redCircleColor android.graphics.Paint instance representing color red.
     * blueCircleColor android.graphics.Paint instance representing color blue.
     * greenCircleColor android.graphics.Paint instance representing color green.
     * purpleCircleColor android.graphics.Paint instance representing color purple.
     * darkBlueTopBarColor android.graphics.Paint instance representing color dark blue.
     */
    private Paint redCircleColor, blueCircleColor,  greenCircleColor, purpleCircleColor, darkBlueTopBarColor;

    /** Constructor for the GameBoard class.*/
    public GameBoard()
    {
        this.redCircleColor = new Paint();
        this.redCircleColor.setColor(ColorsLoader.loadColorByName("red"));

        this.blueCircleColor = new Paint();
        this.blueCircleColor.setColor(ColorsLoader.loadColorByName("blue"));

        this.greenCircleColor = new Paint();
        this.greenCircleColor.setColor(ColorsLoader.loadColorByName("green"));

        this.purpleCircleColor = new Paint();
        this.purpleCircleColor.setColor(ColorsLoader.loadColorByName("purple"));

        this.darkBlueTopBarColor = new Paint();
        this.darkBlueTopBarColor.setColor(ColorsLoader.loadColorByName("darkBlue"));
    }

    /**
     * Gets the color of the corresponding canvas quadrant.
     *
     * @param x float value representing the x-coordinate in pixels.
     * @param y float value representing the y-coordinate in pixels.
     * @return integer value representing the color of the corresponding color.
     */
    public int getQuadrantColor(float x, float y)
    {
        if (this.redTouched(x, y))
            return ColorsLoader.loadColorByName("red");

        else if (this.blueTouched(x, y))
            return ColorsLoader.loadColorByName("blue");

        else if (this.greenTouched(x, y))
            return ColorsLoader.loadColorByName("green");

        else if (this.purpleTouched(x, y))
            return ColorsLoader.loadColorByName("purple");

        return 0;
    }

    /**
     * Checks if the color red is touched.
     *
     * @param x float value representing the x-coordinate in pixels.
     * @param y float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color red is touched.
     */
    public boolean redTouched(float x, float y)
    {
        return (x <= (330f/1080) * GameView.WIDTH) && (y <= (820f/1920) * GameView.HEIGHT && y >= (550f/1920) * GameView.HEIGHT);
    }
    /**
     * Checks if the color green is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color green is touched.
     */
    public boolean greenTouched(float x, float y)
    {
        return (x >= (745f/1080)*GameView.WIDTH) && (y <= (820f/1920)*GameView.HEIGHT && y >= (550f/1920) * GameView.HEIGHT);
    }

    /**
     * Checks if the color blue is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color blue is touched.
     */
    public boolean blueTouched(float x, float y)
    {
        return (x <= (330f/1080)*GameView.WIDTH) && (y >= (1580f/1920)*GameView.HEIGHT);
    }

    /**
     * Checks if the color purple is touched.
     *
     * @param x: float value representing the x-coordinate in pixels.
     * @param y: float value representing the y-coordinate in pixels.
     * @return boolean value representing whether or not the color purple is touched.
     */
    public boolean purpleTouched(float x, float y)
    {
        return (x >= (745f/1080)*GameView.WIDTH) && (y >= (1580f/1920)*GameView.HEIGHT);
    }

    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */
    public void draw(Canvas canvas)
    {

        canvas.drawRect(0f, 0f, GameView.WIDTH, (100f/1080f)*GameView.HEIGHT, this.darkBlueTopBarColor);
        canvas.drawCircle((175f/1080)*GameView.WIDTH,0.9f*GameView.HEIGHT, RADIUS, this.blueCircleColor);
        canvas.drawCircle((905f/1080)*GameView.WIDTH,0.9f*GameView.HEIGHT, RADIUS, this.purpleCircleColor);

        canvas.drawCircle((175f/1080)*GameView.WIDTH, 0.35f*GameView.HEIGHT, RADIUS, this.redCircleColor);
        canvas.drawCircle((905f/1080)*GameView.WIDTH, 0.35f*GameView.HEIGHT, RADIUS, this.greenCircleColor);
    }

    //0.162
    //0.838
}
