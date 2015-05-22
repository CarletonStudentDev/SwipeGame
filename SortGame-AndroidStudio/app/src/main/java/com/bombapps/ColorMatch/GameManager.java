package com.bombapps.ColorMatch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

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
    private static final String LIVESFINISHED = "Out of Lives!",
                                TIMERANOUT = "Out of Time!";

    /** game: Game instance representing the game to be played.*/
    private Game game;

    /** card: Card instance representing the card at center of screen.*/
    private Card card;
    private ArrayList<MoveCard> moveCards = new ArrayList<MoveCard>();
    private ArrayList<MoveCard> toRemove = new ArrayList<MoveCard>();

    private Stroop stroop;

    /** gameBoard: GameBoard instance representing board of the game.*/
    private GameBoard gameBoard;

    /**
     * gameFinished boolean value representing whether the game finished or not.
     * timedOut boolean value representing whether the clock has finished or not.
     * impossible boolean value representing whether the game is impossible or not.
     */
    private boolean gameFinished = false,
                    timedOut,
                    impossible,
                    stroopMode,
                    minusHearts;

    /**
     * score TextObject instance representing the score text displaying on the screen.
     * timer TextObject instance representing the timer text displaying on the screen.
     */
    private TextObject score,
                       timer,
                       plus2seconds;

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
                   emptyLivesBitmap,minusHeartsBitmap,
                    readyBitmap,goBitmap;


    /**
     * gameClock GameClock instance representing the countdown timer of the game.
     */
    private GameClock gameClock;
    private int cardsCorrect=0,plus2secondsSeen=0,minusHeartsSeen=0,readySeen=0,goSeen=0;
    protected boolean soundPlayed;
    private boolean beginGame=true, readyShowing=true;


    /**
     * Constructor for the GameManager class.
     * @param gameTime Long value representing the amount of time at start of game.
     */

    public GameManager(long gameTime, boolean stroopMode, boolean impossibleMode)
    {
        soundPlayed = false;
        gameFinished = false;
        timedOut = false;
        impossible = impossibleMode;
        minusHearts=false;
        this.stroopMode = stroopMode;

        if(stroopMode){
            stroop = new Stroop(impossible);
        }else{
            card = new Card();
        }

        gameBoard = new GameBoard(impossible);
        game = new Game();

        gameClock = new GameClock(gameTime);
        gameClock.addObserver(this);


        timer = new ClockTextObject("10", (525f / 1080) * GameView.WIDTH, (550f / 1701) * GameView.HEIGHT,
                    GameView.typeface, ColorsLoader.loadColorByName("black"), (225f / 1080) * GameView.WIDTH);

        /*
        if (impossible)
            timer.setText(" ∞");
        */

        gameOverScreen = new GameOverScreen(GameView.typeface, ColorsLoader.loadColorByName("white"));

        //demoBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.demo);
        //demoBitmap = Bitmap.createScaledBitmap(demoBitmap, (int) (GameView.WIDTH), (int) (GameView.WIDTH), true);

        fullLivesBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.fullheart);
        fullLivesBitmap = Bitmap.createScaledBitmap(fullLivesBitmap, (int)((100f/1080) * GameView.WIDTH), (int)((100f/1080) * GameView.WIDTH), true);

        emptyLivesBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.blankheart);
        emptyLivesBitmap = Bitmap.createScaledBitmap(emptyLivesBitmap, (int) ((100f / 1080) * GameView.WIDTH), (int) ((100f / 1080) * GameView.WIDTH), true);

        minusHeartsBitmap= BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.minusfullheart2);
        minusHeartsBitmap = Bitmap.createScaledBitmap(minusHeartsBitmap, (int) ((200f / 1080) * GameView.WIDTH), (int) ((200f / 1080) * GameView.WIDTH), true);

        readyBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.ready2);
        readyBitmap = Bitmap.createScaledBitmap(readyBitmap, (int) ((750f / 1080) * GameView.WIDTH), (int) ((300f / 1080) * GameView.WIDTH), true);

        goBitmap = BitmapFactory.decodeResource(GameView.activity.getResources(), R.drawable.go2);
        goBitmap = Bitmap.createScaledBitmap(goBitmap, (int) ((600f / 1080) * GameView.WIDTH), (int) ((300f / 1080) * GameView.WIDTH), true);

        score = new TextObject("" + game.getScore(), (350f/1080)*GameView.WIDTH, (125f/1701)*GameView.HEIGHT,
                              GameView.typeface, ColorsLoader.loadColorByName("white"), (150f/1080) * GameView.WIDTH);

        multiplierBar = new MultiplierBar(game.getMultiplierNum(), game.getBarNum(),
                                          GameView.typeface, ColorsLoader.loadColorByName("white"));

        plus2seconds = new ClockTextObject("+1", (525f / 1080) * GameView.WIDTH, (775f / 1701) * GameView.HEIGHT,
                GameView.typeface, ColorsLoader.loadColorByName("green"), (225f / 1080) * GameView.WIDTH);

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
            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                if (gameFinished)
                {

                    if(gameOverScreen.getWaitTime() == 0) {
                        if (this.gameOverScreen.getGameOverButton(event.getX(), event.getY()) == 1)
                            GameView.activity.finish();

                        else if (this.gameOverScreen.getGameOverButton(event.getX(), event.getY()) == 2)
                            GameView.activity.recreate();
                    }

                }else{
                    if (stroopMode || impossible) {
                        if (this.gameBoard.getQuadrantColor(event.getX(), event.getY()) == this.stroop.getColorId())
                            this.correct();
                        else if (this.gameBoard.getQuadrantColor(event.getX(), event.getY()) != 0)
                            this.incorrect();
                    } else {
                        if (this.gameBoard.getQuadrantColor(event.getX(), event.getY()) == this.card.getColorId())
                            this.correct();
                        else if (this.gameBoard.getQuadrantColor(event.getX(), event.getY()) != 0)
                            this.incorrect();
                    }
                    soundPlayed = false;
                }
            }

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
        cardsCorrect ++;
        game.correct();
        score.setText("" + game.getScore());
        this.setMultiValueCardColor();

        if((cardsCorrect%3)==0 )
            gameClock.addTime(950L);
            plus2secondsSeen = 0;

        if(stroopMode){
            stroop.correctStroop();
        }else{
            moveCards.add(new MoveCard(card.getColorId(), card.getXCoord(), card.getYCoord()));
            card.generateNewColor();
        }

        if(impossible)
            gameBoard.randomizePiles(cardsCorrect);
    }


    /**
     * Notifies the game of incorrect, decrements the lives, generates a new card and
     * updates the multiplier bar accordingly.
     */

    private void incorrect()
    {
        game.incorrect();
        minusHeartsSeen=0;
        minusHearts=true;
        this.setMultiValueCardColor();
    }


    /**
     * Generates a new card and updates the multiplier bar accordingly.
     */

    private void setMultiValueCardColor()
    {
        multiplierBar.setMultiplierValues(game.getMultiplierNum(), game.getBarNum());
    }

    private void checkHighScore()
    {
        //if (game.getScore() > GooglePlayServices.getHighScore())
            //GooglePlayServices.setHighScore(game.getScore());
    }

    public int getHighScore (){

        if (stroopMode){
            ScoreDataSource.createStroopScore(game.getScore());
            return ScoreDataSource.getStroopHighScore();
        }else if(impossible){
            ScoreDataSource.createEndlessScore(game.getScore());
            return ScoreDataSource.getEndlessHighScore();
        }else{
            ScoreDataSource.createNormalScore(game.getScore());
            return ScoreDataSource.getNormalHighScore();
        }

    }


    /**
     * Checks if gameover has occurred and displays the appropriate message onto the screen.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    private void gameOver(Canvas canvas)
    {
        if ((game.getLivesFinished() || timedOut) && !gameFinished)
        {
            gameFinished = true;

            if (timedOut) {
                gameOverScreen.setLossReason(TIMERANOUT);

            }else if (game.getLivesFinished()) {
                gameOverScreen.setLossReason(LIVESFINISHED);
            }

            this.checkHighScore();

            gameClock.stopTime();

            gameOverScreen.setScores(game.getScore(), getHighScore(), cardsCorrect, gameClock.secondsPassed, stroopMode);

        }

        if (gameFinished) {
            gameOverScreen.draw(canvas);
            if(!soundPlayed)
            {
                MediaSounds.loadPlaySound(R.raw.gameover4, 1, 1f);
                GameView.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if(MyActivity.count == 6) {
                                GameView.activity.requestNewInterstitial();
                                MyActivity.count = 0;
                            }
                        }
                    }

                );
                soundPlayed = true;
            }
        }
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas) {

        //yif(true) {
            gameBoard.draw(canvas);

            if (stroopMode) {
                stroop.draw(canvas);
            } else {
                card.draw(canvas);
            }

            score.draw(canvas);
            multiplierBar.draw(canvas);
            timer.draw(canvas);

            for (int i = 0; i < 3; i++)
                canvas.drawBitmap(emptyLivesBitmap, ((700f + (i * 120)) / 1080) * GameView.WIDTH, (37f / 1701) * GameView.HEIGHT, null);

            for (int i = 0; i < game.getLives(); i++)
                canvas.drawBitmap(fullLivesBitmap, ((700f + (i * 120)) / 1080) * GameView.WIDTH, (37f / 1701) * GameView.HEIGHT, null);

            for (MoveCard mc : moveCards) {
                if (mc.inQuadrant()) {
                    toRemove.add(mc);
                }
                mc.draw(canvas);

            }
            moveCards.removeAll(toRemove);
            if (!(this.timedOut) && !beginGame)
                timer.setText("" + gameClock.getRemainingTimeLeft());

            if (minusHeartsSeen <= 20 && minusHearts) {
                canvas.drawBitmap(minusHeartsBitmap, (425f / 1080) * GameView.WIDTH, (560f / 1701) * GameView.HEIGHT + minusHeartsSeen * 2, null);
                minusHeartsSeen++;
                if (minusHeartsSeen == 20) {
                    minusHearts = false;
                }
            }

            if ((cardsCorrect % 3) == 0 && cardsCorrect != 0 && plus2secondsSeen < 20 && !minusHearts) {
                plus2seconds.draw(canvas);
                plus2seconds.setYcoordinate(plus2seconds.getYcoordinate() - 2);
                plus2secondsSeen++;

                if (plus2secondsSeen == 20) {
                    plus2seconds.setYcoordinate((750f / 1701) * GameView.HEIGHT);
                }
            }

        if (beginGame && readySeen <= 20 ){
            canvas.drawBitmap(readyBitmap, (150f / 1080) * GameView.WIDTH, (560f / 1701) * GameView.HEIGHT - readySeen * 2, null);
            readySeen++;

                if (readySeen == 20) {
                    readyShowing = false;
                }
            }

        if (beginGame && goSeen <= 20 && !readyShowing){
            //canvas.drawBitmap(goBitmap, (230f / 1080) * GameView.WIDTH, (560f / 1701) * GameView.HEIGHT - goSeen * 2, null);
            canvas.drawBitmap(goBitmap, (250f / 1080) * GameView.WIDTH, (520f / 1701) * GameView.HEIGHT , null);
            goSeen++;

                if (goSeen == 20) {
                    beginGame = false;
                }
            }

            gameOver(canvas);
        /*}else{
            canvas.drawBitmap(demoBitmap, (540f / 1080) * GameView.WIDTH, (860f / 1701) * GameView.HEIGHT, null);

        }*/
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


    public boolean getGameOver(){
        return gameFinished;
    }
}
