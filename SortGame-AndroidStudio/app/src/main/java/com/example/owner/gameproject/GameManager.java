package com.example.owner.gameproject;

import android.view.MotionEvent;

/**
 * GameManager class manages the logic of the game.
 *
 * @author Robert
 * @version 1.3
 * @since 2015-02-27
 *
 */

public class GameManager
{
    /** game: Game instance representing the game to be played.*/
    private Game game;

    /** card: Card instance representing the card at center of screen.*/
    private Card card;

    /** view: GameView instance representing the view of the app.*/
    private GameView view;

    /** gameBoard: GameBoard instance representing board of the game.*/
    private GameBoard gameBoard;

    /** start: boolean value representing the start of the application.*/
    private boolean start;

    /**
     * centerX: static float value representing the x-coordinate of screen center in pixels.
     * centerY: static float value representing the y-coordinate of screen center in pixels.
     * scaleX: static float value representing the scale of images depending on centerX
     * scaleY: static float value representing the scale of images depending on centerY
     */
    public static float centerX,
                        centerY,
                        scaleX,
                        scaleY;

    public GameManager(GameView gameview)
    {
        view = gameview;
        start = true;

        card = new Card();
        gameBoard = new GameBoard();
        game = new Game();

        //lives = new TextObject();
        //lives.setText(game.getLives());

        //score = new TextObject();
        //score.setText(game.getScore());

        //multiplierBar = new MultiplierBar();
        //multiplierBar.setMultiplierValues(game.getMultiplierNum(), game.getBarNum());
    }

    /**
     * Checks whether or not an motion event has occurred. It is
     * also responsible for determining a correct, incorrect match or
     * a time out has occurred depending on the motion event.
     *
     * @see android.view.MotionEvent;
     *
     * @param event: MotionEvent instance representing the
     * motion of event that has occurred.
     *
     * @return bool: boolean representing whether or not
     * an event has occurred.
     */
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event != null)
        {
            float r = (float)this.view.getHeight() / this.view.getWidth();

            // convert touch coordinates into OpenGL coordinates
            float newX = (-(event.getX() * 2) / this.view.getWidth() + 1f) / r;
            float newY = -(event.getY() * 2) / this.view.getHeight() + 1f;

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                /*
                if (this.game.getGameOver())
                {
                    this.clock.stopClock();

                    if (this.gameBoard.getGameOverButton(newX, newY) == 2)
                        activity.finish();

                    else if (this.gameBoard.getGameOverButton(newX, newY) == 1)
                        activity.recreate();
                }

                else
                {*/
                    if (this.gameBoard.getQuadrantColor(newX, newY) == this.card.getColorId())
                        this.correct();

                    else if (this.gameBoard.getQuadrantColor(newX, newY) != 0)
                        this.incorrect();
                //}
            }

             /*if (event.getAction() == MotionEvent.ACTION_MOVE)
             {
                float deltaX = (x - mPreviousX) / r / 2f;
                float deltaY = (y - mPreviousY) / r / 2f;
                if(renderer.card.inShape(newX, newY))
                {
                   renderer.mDeltaX += deltaX;
                   renderer.mDeltaY += deltaY;
                }

             }*/
                //mPreviousX = x;
                //mPreviousY = y;
            return true;
        }
        return false;
    }

    private void correct()
    {
        game.correct();
        //score.setText(game.getScore());
        this.genNewColorSetMultValue();
    }

    private void incorrect()
    {
        game.incorrect();
        //lives.setText(game.getLives());
        this.genNewColorSetMultValue();
    }

    private void genNewColorSetMultValue()
    {
        card.generateNewColor();
        //multiplier.setMultiplierValues(game.getMultiplierNum(), game.getBarNum());
    }

    public void draw(){
        if(start){
            centerX = view.getWidth() / 2;
            centerY = view.getHeight() / 2;
            scaleX = centerX / 2;
            scaleY = centerY / 2;
            start = false;
        }
        card.draw();
        gameBoard.draw();

        //lives.draw();
        //score.draw();
        //multiplierBar.draw();
    }
}
