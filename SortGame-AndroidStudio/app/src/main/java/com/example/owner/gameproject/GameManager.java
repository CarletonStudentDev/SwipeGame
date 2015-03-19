package com.example.owner.gameproject;

import android.graphics.Canvas;
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

    private static final String EMPTYSTRING = "",
                                LIVESFINISHED = "OUT OF LIVES!",
                                TIMERANOUT = "TIME RAN OUT!";

    /** game: Game instance representing the game to be played.*/
    private Game game;

    /** card: Card instance representing the card at center of screen.*/
    private Card card;

    /** view: GameView instance representing the view of the app.*/
    private GameView view;

    /** gameBoard: GameBoard instance representing board of the game.*/
    private GameBoard gameBoard;

    /**
     * gameFinished boolean value representing whether the game finished or not.
     */
    private boolean gameFinished,
                    timedOut;

    private TextObject score;

    private GameOverScreen gameOverScreen;


    public GameManager(GameView gameview)
    {
        view = gameview;
        gameFinished = false;
        timedOut = false;

        card = new Card();
        gameBoard = new GameBoard();
        game = new Game();


        gameOverScreen = new GameOverScreen(GameView.typeface,
                         GameView.instance.getResources().getColor(R.color.white));

        //lives = new TextObject();
        //lives.setText(game.getLives());

        // temporary values
        score = new TextObject(EMPTYSTRING + game.getScore(), 100f, 200f, GameView.typeface,
                               GameView.instance.getResources().getColor(R.color.blue), 100f);

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
     * @param event MotionEvent instance representing the
     * motion of event that has occurred.
     *
     * @return boolean representing whether or not
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

            if(event.getAction() == MotionEvent.ACTION_UP)
            {

                if (gameFinished)
                {
                    /*this.clock.stopClock();

                    if (this.gameOverScreen.getGameOverButton(newX, newY) == 2)
                        GameView.activity.finish();

                    else if (this.gameOverScreen.getGameOverButton(newX, newY) == 1)
                        GameView.activity.recreate();*/

                }

                else
                {
                    if (this.gameBoard.getQuadrantColor(newX, newY) == this.card.getColorId())
                        this.correct();

                    else if (this.gameBoard.getQuadrantColor(newX, newY) != 0)
                        this.incorrect();

                }
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
        score.setText(EMPTYSTRING + game.getScore());
        this.setMultiValueCardColor();
    }

    private void incorrect()
    {
        game.incorrect();
        //lives.setText(game.getLives());
        this.setMultiValueCardColor();
    }

    private void setMultiValueCardColor()
    {
        card.generateNewColor();
        //multiplier.setMultiplierValues(game.getMultiplierNum(), game.getBarNum());
    }


    private void checkHighScore()
    {
        //if (game.getScore() > GooglePlayServices.getHighScore())
            //GooglePlayServices.setHighScore(game.getScore());
    }


    private void gameOver(Canvas canvas)
    {
        if (game.getLivesFinished() || timedOut)
        {
            gameFinished = true;

            if (timedOut)
                gameOverScreen.setLossReason(TIMERANOUT);

            else if (game.getLivesFinished())
                gameOverScreen.setLossReason(LIVESFINISHED);

            this.checkHighScore();

            gameOverScreen.setScores(game.getScore(), 0);

            gameOverScreen.draw(canvas);
        }

    }

    public void draw(Canvas canvas)
    {

        card.draw(canvas);
        gameBoard.draw(canvas);

        //lives.draw();
        score.draw(canvas);
        //multiplierBar.draw();

        gameOver(canvas);

    }
}
