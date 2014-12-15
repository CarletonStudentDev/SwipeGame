package com.example.owner.gameproject;

/**
 * Created by home on 29/11/2014.
 */
public abstract class DrawableObject {

    /*
     * not a very useful class for this game, but for future games
     * move functions and collision functions will be put in here
     *
     * this will be the object that we create to hold the object parameters (x, y, images)
     * (see card class for an example)
     *
     * Example constructor for a drawable object
     *
     *      public Card (Resources resources, float x, float y, int colorId) {
     *
     *          float squareColor[] = GraphicsHelper.RGBArray(resources, colorId);
     *          square = new Square (x, y, width, length, squareColor);
     *      }
     *
     */

    public abstract void move(float x, float y);

    public abstract void draw(float[] mMVPMatrix);
}
