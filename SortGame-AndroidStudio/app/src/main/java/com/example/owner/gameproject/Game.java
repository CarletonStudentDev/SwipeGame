package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Game class is created in the GameView
 * Used to bring the functions of the view into a more manageable space
 *
 * @author Robert
 *
 * @version V.1
 * @since 2014-10-28
 * Created by home on 26/10/2014.
 */
public class Game {

    /**
     * X position of card
     * Should later be called from a general layout class
     */
    private int x = 0;
    /**
     * Y position of card
     * Should later be called from a general layout class
     */
    private int y = 0;
    /**
     * XSpeed used to store the original XSpeed of the card
     * Wont be needed when we start doing everything else
     */
    private int xSpeed = 0;

    /**
     * Card object that will be drawn to the screen
     */

    private Card card;

    /**
     * Constructor for the Game class
     * creates new objects
     *
     * @param view -> view object to grab resources and screen size from
     */
    public Game(SurfaceView view){
        card = new Card(view, x, y);
    }

    /**
     * Method which is later called in onDraw
     * used to clear code up by moving all the logic to here
     */
    private void update(){

    }

    /**
     * onDraw is called in the gameLoop, and runs in "real time"
     * used to draw things to screen
     *
     * @param canvas -> canvas object that we need to draw to
     */
    public void onDraw(Canvas canvas){
        update();
        card.onDraw(canvas);
    }

    /**
     * Called in the GameView onTouchListener to find where the touch was made
     *
     * @param event -> MotionEvent used to find out what type of motion was made
     */
    public void onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        int eventAction = event.getAction();

        switch (eventAction) {

            case MotionEvent.ACTION_DOWN:

                card.setX(x);
                card.setY(y);
                xSpeed = card.getXSpeed();
                card.setXSpeed(0);

                break;

            case MotionEvent.ACTION_MOVE:
                card.setX(x);
                card.setY(y);
                break;

            case MotionEvent.ACTION_UP:
                card.setXSpeed(xSpeed);
                break;
        }
    }

}
