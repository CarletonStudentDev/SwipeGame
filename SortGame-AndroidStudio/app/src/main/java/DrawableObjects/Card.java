package DrawableObjects;


import android.content.Context;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.GraphicsHelper;
import OpenGL.Square;


/**
 * Card represents the element containing a pattern
 * in the center of the screen which the Player
 * matches it to one of the piles at the sides of
 * the screen.
 *
 * This class is a subclass of DrawableObject.
 * @see OpenGL.DrawableObject
 *
 *
 * @author Zack Munich
 * @author Robert Fernandes
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.7
 * @since 2015-01-04
 *
 */


public class Card extends DrawableObject
{

    /**
     * WIDTH: float value representing the width of the Card
     *
     * LENGTH: float value representing the length of the Card.
     *
     */

    private static final float WIDTH = 0.5f,
                               LENGTH = 0.5f;


    /**
     * square: Square instance representing the shape which
     *         will represent the Card on the screen.
     *
     */

    private Square square;


    /**
     * squareColor: float [] instance representing the different
     *              colors that a square can be.
     *
     */

    private float[] squareColor;


    /**
     * xCoordinate: float value representing the x-coordinate
     *              of the Card.
     *
     * yCoordinate: float value representing the y-coordinate
     *              of the Card.
     *
     */

    private float xCoordinate,
                  yCoordinate;


    /**
     * colorId: integer value representing the color of the
     *          Card.
     *
     */

    private int colorId;



    /**
     * Constructor for the Card class.
     *
     * @param context: Context instance representing the
     *                 Context of the app.
     * @param xCoordinate: float value representing the
     *                     x-coordinate of the Card.
     * @param yCoordinate: float value representing the
     *                     y-coordinate of the Card.
     * @param colorId: integer value representing the color
     *                 of the Card given by the CardGenerator.
     */

    public Card (Context context, float xCoordinate, float yCoordinate, int colorId)
    {
        this.colorId = this.getColor(colorId);
        this.squareColor = GraphicsHelper.RGBArray(context, this.colorId);
        this.square = new Square(xCoordinate, yCoordinate, WIDTH, LENGTH, this.squareColor);
    }


    /**
     * Getter for the shape of the Card.
     *
     * @return square: Square instance representing the shape which
     *                 will represent the Card on the screen.
     *
     */

    public Square getSquare()
    {
        return this.square;
    }


    /**
     * Getter for the color of the square.
     *
     * @return squareColor: float [] instance representing the different
     *                      colors that a square can be.
     *
     */

    public float[] getSquareColor()
    {
        return this.squareColor;
    }


    /**
     * Getter for for the x-coordinate of the Card.
     *
     * @return xCoordinate: float value representing the x-coordinate
     *                      of the Card.
     *
     */

    public float getXCoordinate()
    {
        return this.xCoordinate;
    }


    /**
     * Getter for for the y-coordinate of the Card.
     *
     * @return yCoordinate: float value representing the y-coordinate
     *                      of the Card.
     *
     */

    public float getYCoordinate()
    {
        return this.yCoordinate;
    }


    /**
     * Gives the corresponding color according to the
     * color id.
     *
     * @param colorId: integer value representing the color
     *                 of the Card given by the CardGenerator.
     *
     * @return color: integer value representing the corresponding
     *                color determined by the colorId.
     *
     */

    private int getColor(int colorId)
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
     * Moves the Card to the new x- and y-coordinates.
     *
     * @see OpenGL.DrawableObject
     *
     *
     * @param newXCoordinate: float value representing the new
     *                        x-coordinate of the Card.
     * @param newYCoordinate: value representing the new
     *                        y-coordinate of the Card.
     *
     */

    public void move(float newXCoordinate, float newYCoordinate)
    {
        this.xCoordinate = newXCoordinate;
        this.yCoordinate = newYCoordinate;
    }


    /**
     * Draws the card to the screen.
     *
     * @see OpenGL.DrawableObject
     *
     *
     * @param mMVPMatrix: float [] representing the matrix.
     *
     */

    public void draw(float[] mMVPMatrix)
    {
        this.square.draw(mMVPMatrix);
        this.xCoordinate = square.x;
        this.yCoordinate = square.y;
    }


    /**
     * Checks if the drawn object is within the bounds
     * of the restriction set by the WIDTH and LENGTH.
     *
     * @param x: float representing the x-coordinate of
     *           the Card.
     * @param y: float representing the y-coordinate of
     *           the Card.
     *
     * @return bool: boolean value representing whether or
     *               not the drawn object is within the
     *               bounds.
     *
     */

    public boolean inShape(float x, float y)
    {
        return (x >= this.xCoordinate - WIDTH) &&
               (x <= this.xCoordinate + WIDTH) &&
               (y >= this.yCoordinate - LENGTH) &&
               (y <= this.yCoordinate + LENGTH);
    }


    /**
     * Getter for the color id.
     *
     * @return colorId: integer value representing the color
     *                  of the Card given by the CardGenerator.
     *
     */

    public int getColorId()
    {
        return this.colorId;
    }

}
