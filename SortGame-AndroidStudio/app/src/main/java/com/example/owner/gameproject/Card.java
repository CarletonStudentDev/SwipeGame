package com.example.owner.gameproject;

<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Drawable card object with all the getters and setters
 *
 * @author Robert
 *
 * @version V.1
 * @since 2014-10-28
 */
public class Card {

    /**
     * bmp stores the bitmap image
     */
    private Bitmap bmp;
    /**
     * x and y store the position of the card
     * xSpeed initializes the speed of the card
     */
    private int x, y, xSpeed = 4;
    /**
     * Stores the view which holds the resources
     */
    private SurfaceView view;

    /**
     * Card class constructor
     * initializes the position of the card and its image
     *
     * @param view -> used to find the screen size and get the resources
     * @param x -> x position of the card
     * @param y -> y position of the card
     */
    public Card(SurfaceView view, int x, int y) {
        this.view = view;
        this.x = x;
        this.y = y;
        bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.ic_launcher);
    }

    /**
     * Method which is later called in onDraw
     * used to clear code up by moving all the logic to here
     */
    public void update() {

        if (x + xSpeed >= view.getWidth() - bmp.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (x + xSpeed <= 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
    }
    /**
     * onDraw is called in the gameLoop, and runs in "real time"
     * used to draw things to screen
     *
     * @param canvas -> canvas object that we need to draw to
     */
    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }

    /**
     * getters
     */
    /**
     * @return -> card height
     */
    public int getHeight(){
        return bmp.getHeight();
    }

    /**
     * @return -> card width
     */
    public int getWidth(){
        return bmp.getWidth();
    }

    /**
     * @return -> card x position
     */
    public int getX(){
        return x;
    }

    /**
     * @return -> card y position
     */
    public int getY(){
        return y;
    }

    /**
     * @return -> x speed
     */
    public int getXSpeed(){
        return xSpeed;
    }

    /**
     * setters
     */

    /**
     * @param x -> sets card x position
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * @param y -> sets card y position
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * @param xSpeed -> sets card xSpeed
     */
    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }
}
=======
import android.content.res.Resources;

public class Card extends DrawableObject {

    public Square square;
    private float[] squareColor;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Resources resources, float x, float y, int colorId) {
        squareColor = GraphicsHelper.RGBArray(resources, colorId);
        square = new Square (x, y, width, length, squareColor);
    }

    public void move(float x1, float y1){
        this.x = x1;
        this.y = y1;
    }

    public void draw(float[] mMVPMatrix){
        square.draw(mMVPMatrix);
        x = square.x;
        y = square.y;
    }

    public boolean inShape(float x, float y){
        return (x >= this.x - width) &&
               (x <= this.x + width) &&
               (y >= this.y - length) &&
               (y <= this.y + length);
    }
}
>>>>>>> OpenGL
