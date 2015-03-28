package com.example.owner.gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.Observable;
import java.util.Observer;

/**
 * GameManager class manages the logic of the game.
 *
 * @author Robert
 * @version 1.3
 * @since 2015-02-27
 *
 */

public class GameManager implements Observer
{

    /**
     * LIVESFINISHED String constant representing the text displayed on game over screen for
     *               lossing all the lives.
     * TIMERANOUT String constant representing the text displayed on game over screen when the
     *            clock has finished.
     */
    private static final String LIVESFINISHED = "OUT OF LIVES!",
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
     * timedOut boolean value representing whether the clock has finished or not.
     * endless boolean value representing whether the game is endless or not.
     */
    private boolean gameFinished,
                    timedOut,
                    endless;

    /**
     * score TextObject instance representing the score text displaying on the screen.
     * timer TextObject instance representing the timer text displaying on the screen.
     */
    private TextObject score,
                       timer;

    /**
     * gameOverScreen GameOverScreen instance representing the stats of the game displayed
     *                at end of game.
     */
    private GameOverScreen gameOverScreen;

    /**
     * multiplierBar MultiplierBar instance representing the displayed multiplier bar.
     */
    private MultiplierBar multiplierBar;


    /**
     * fullLivesBitmap Bitmap instance representing the image used to display the number of
     *                 lives left in the game.
     * emptyLivesBitmap Bitmap instance representing the image used to display the total number
     *                  of lives in the game.
     */
    private Bitmap fullLivesBitmap,
                   emptyLivesBitmap;


    /**
     * gameClock GameClock instance representing the countdown timer of the game.
     */
    private GameClock gameClock;


    /**
     * Constructor for the GameManager class.
     * @param gameview GameView instance representing the SurfaceView of the app.
     * @param gameTime Long value representing the amount of time at start of game.
     */

    public GameManager(GameView gameview, long gameTime)
    {
        view = gameview;
        gameFinished = false;
        timedOut = false;
        endless = false;

        card = new Card();
        gameBoard = new GameBoard();
        game = new Game();

        if (gameTime < 1)
            endless = true;

        gameClock = new GameClock(gameTime);
        gameClock.addObserver(this);

        timer = new ClockTextObject(""+gameClock.getRemainingTimeLeft(), (525f/1080)*GameView.WIDTH,(550f/1701)*GameView.HEIGHT,
                GameView.typeface, ColorsLoader.loadColorByName("black"), (225f/1080) * GameView.WIDTH);

        gameOverScreen = new GameOverScreen(view, GameView.typeface, ColorsLoader.loadColorByName("white"));

        fullLivesBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.fullheart);
        fullLivesBitmap = Bitmap.createScaledBitmap(fullLivesBitmap, (int)((100f/1080) * GameView.WIDTH), (int)((100f/1080) * GameView.WIDTH), true);

        emptyLivesBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.blankheart);
        emptyLivesBitmap = Bitmap.createScaledBitmap(emptyLivesBitmap, (int)((100f/1080) * GameView.WIDTH), (int)((100f/1080) * GameView.WIDTH), true);


        score = new TextObject("" + game.getScore(), (350f/1080)*GameView.WIDTH, (125f/1701)*GameView.HEIGHT,
                              GameView.typeface, ColorsLoader.loadColorByName("white"), (150f/1080) * GameView.WIDTH);

        multiplierBar = new MultiplierBar(game.getMultiplierNum(), game.getBarNum(),
                                          GameView.typeface, ColorsLoader.loadColorByName("white"));

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
                    gameClock.stopTime();

                    if (this.gameOverScreen.getGameOverButton(newX, newY) == 2)
                        GameView.activity.finish();

                    else if (this.gameOverScreen.getGameOverButton(newX, newY) == 1)
                        GameView.activity.recreate();

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


    /**
     * Notifies the game of correct, updates the score, generates a new card and
     * updates the multiplier bar accordingly.
     */

    private void correct()
    {
        game.correct();
        score.setText("" + game.getScore());
        this.setMultiValueCardColor();

        if (!endless)
            gameClock.addTime(1000L);
    }


    /**
     * Notifies the game of incorrect, decrements the lives, generates a new card and
     * updates the multiplier bar accordingly.
     */

    private void incorrect()
    {
        game.incorrect();

        this.setMultiValueCardColor();
    }


    /**
     * Generates a new card and updates the multiplier bar accordingly.
     */

    private void setMultiValueCardColor()
    {
        card.generateNewColor();
        multiplierBar.setMultiplierValues(game.getMultiplierNum(), game.getBarNum());
    }


    private void checkHighScore()
    {
        //if (game.getScore() > GooglePlayServices.getHighScore())
            //GooglePlayServices.setHighScore(game.getScore());
    }


    /**
     * Checks if gameOver has occurred and displays the appropriate message onto the screen.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

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


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {
        card.draw(canvas);
        gameBoard.draw(canvas);

        score.draw(canvas);
        multiplierBar.draw(canvas);
        timer.draw(canvas);

        for (int i = 0; i < 3; i++)
            canvas.drawBitmap(emptyLivesBitmap, ((700f+(i*120))/1080)*GameView.WIDTH, (37f/1701)*GameView.HEIGHT, null);

        for(int i = 0; i < game.getLives(); i++)
            canvas.drawBitmap(fullLivesBitmap, ((700f+(i*120))/1080)*GameView.WIDTH, (37f/1701)*GameView.HEIGHT, null);

        gameOver(canvas);

        if (!this.timedOut)
            timer.setText(""+gameClock.getRemainingTimeLeft());
    }

    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link java.util.Observable} object.
     * @param data       the data passed to {@link java.util.Observable#notifyObservers(Object)}.
     */
    @Override
    public void update(Observable observable, Object data)
    {
        this.timedOut = (boolean) data;
        timer.setText("0");
    }
}
