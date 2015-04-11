package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

/**
 * GameOverScreen class displays the reason for the loss
 * of the game, the current score of the game, the highest
 * achieved score since first playing the game, and options
 * to either go back to main menu or to play again.
 *
 * @author Jeton Sinoimeri
 * @version 1.5
 * @since 2015-03-18
 *
 */

public class GameOverScreen
{
    /**
     * SCORESTRING String constant representing the word Score to be displayed on screen.
     * HIGHSCORE String constant representing the word Highscore to be displayed on screen.
     *
     */

    private static final String SCORESTRING = "Score: ",
                                HIGHSCORESTRING = "High Score: ",
                                CARDSPERSEC = "Cards/Sec: ",
                            STROOPSPERSEC = "Stroops/Sec: ";


    /**
     * score TextObject instance representing the score to be displayed on screen.
     * highScore TextObject instance representing the highScore to be displayed on screen.
     * lossReason TextObject instance representing the reason of loss to be displayed on screen.
     * backToMenu TextObject instance representing the text of the button to go back to main menu.
     * retryGame TextObject instance representing the text of the button to retry the game.
     *
     */

    private TextObject score,scoreNum,
                       highScore,highScoreNum,
                       lossReason,
                       backToMenu,
                       retryGame,
                        cardsPerSec,
                        cardsPerSecNum,
                        stroopsPerSec;


    /**
     * leftCoordinate float value representing the left coordinate of game over screen rectangle.
     * topCoordinate float value representing the top coordinate of game over screen rectangle.
     * rightCoordinate float value representing the right coordinate of game over screen rectangle.
     * bottomCoordinate float value representing the bottom coordinate of game over screen rectangle.
     *
     */

    private float leftCoordinate,
                  topCoordinate,
                  rightCoordinate,
                  bottomCoordinate,
                  calculatedCardsPerSec;


    /**
     * paint Paint instance representing the color, and font of the TextObject.
     * paintSquares Paint instance representing the color of the game over buttons.
     *
     */

    private Paint paint, paintSquares;

    private boolean stroopMode;

    private int waitTime = 40;

    /**
     * Constructor for the GameOverScreen class.
     *
     * @param typeface Typeface object representing the font type of the text.
     * @param textColor integer value obtained from getResources() representing
     *                  the color of the text.
     *
     */

    public GameOverScreen(Typeface typeface, int textColor)
    {
        this.lossReason = new TextObject("", (540f/1080)*GameView.WIDTH, (350f/1701)*GameView.HEIGHT, typeface, textColor, (150f/1080) * GameView.WIDTH);

        this.score = new TextObject(SCORESTRING, (540f/1080)*GameView.WIDTH, (500f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.scoreNum = new TextObject("", (540f/1080)*GameView.WIDTH, (650f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);

        this.highScore = new TextObject(HIGHSCORESTRING, (540f/1080)*GameView.WIDTH, (800f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.highScoreNum = new TextObject("", (540f/1080)*GameView.WIDTH, (950f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);

        this.cardsPerSec = new TextObject(CARDSPERSEC, (540f/1080)*GameView.WIDTH, (1100f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.stroopsPerSec = new TextObject(STROOPSPERSEC, (560f/1080)*GameView.WIDTH, (1100f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.cardsPerSecNum = new TextObject("", (540f/1080)*GameView.WIDTH, (1250f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);

        this.backToMenu = new TextObject("Back", (350f/1080)*GameView.WIDTH, (1450f/1701)*GameView.HEIGHT, typeface, textColor, (100f/1080) * GameView.WIDTH);
        this.retryGame = new TextObject("Retry", (750f/1080)*GameView.WIDTH, (1450f/1701)*GameView.HEIGHT, typeface, textColor, (100f/1080) * GameView.WIDTH);

        this.leftCoordinate = 0.1f * GameView.WIDTH;
        this.topCoordinate = 0.1f * GameView.HEIGHT;
        this.rightCoordinate = 0.9f * GameView.WIDTH;
        this.bottomCoordinate =  0.95f * GameView.HEIGHT;

        this.paint = new Paint();
        this.paint.setColor(ColorsLoader.loadColorByName("darkBlue"));

        this.paintSquares=new Paint();
        this.paintSquares.setColor(ColorsLoader.loadColorByName("blue"));
    }


    /**
     * Setter for the score and highScore of the game to be displayed on screen.
     *
     * @param score int value representing the current score of the game.
     * @param highScore long value representing the highest score achieved
     *                  since playing the game.
     *
     */

    public void setScores(int score, int highScore, int cardsDone, int secondsPassed, boolean stroopMode)
    {
        this.stroopMode = stroopMode;
        this.scoreNum.setText("" + score);
        this.highScoreNum.setText("" + highScore);
        calculatedCardsPerSec = (float) cardsDone / (float) secondsPassed;
        this.cardsPerSecNum.setText("" + String.format("%.3f" , calculatedCardsPerSec));
    }


    /**
     * Getter for the game over buttons.
     *
     * @param xCoordinate float value representing the x-coordinate in pixels.
     * @param yCoordinate float value representing the y-coordinate in pixels.
     * @return integer representing the corresponding game over button.
     *
     */

    public int getGameOverButton(float xCoordinate, float yCoordinate)
    {
        if( xCoordinate >= (200f/1080)*GameView.WIDTH && xCoordinate <= (500f/1080)*GameView.WIDTH
                && yCoordinate >= (1325f/1701)*GameView.HEIGHT && yCoordinate <= (1520f/1701)*GameView.HEIGHT)
            return 1;

        else if(xCoordinate >=(600f/1080)*GameView.WIDTH && xCoordinate <= (900f/1080)*GameView.WIDTH
               && yCoordinate>=(1325f/1701)*GameView.HEIGHT && yCoordinate <= (1520f/1701)*GameView.HEIGHT)
            return 2;

        return 0;

    }

    /**
     * Setter for the reason the game was lost.
     *
     * @param lossReason String object representing the reason the game was lost.
     *
     */

    public void setLossReason(String lossReason)
    {
        this.lossReason.setText(lossReason);
    }


    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     *
     */

    public void draw(Canvas canvas) {
        canvas.drawRect(leftCoordinate, topCoordinate, rightCoordinate, bottomCoordinate, paint);
        this.lossReason.draw(canvas);

        this.score.draw(canvas);
        this.scoreNum.draw(canvas);

        this.highScore.draw(canvas);
        this.highScoreNum.draw(canvas);

        if (stroopMode == true) {
            this.stroopsPerSec.draw(canvas);
        } else {
            this.cardsPerSec.draw(canvas);
        }
        this.cardsPerSecNum.draw(canvas);


        canvas.drawRect((200f / 1080) * GameView.WIDTH, (1325f / 1701) * GameView.HEIGHT, (500f / 1080) * GameView.WIDTH, (1520f / 1701) * GameView.HEIGHT, paintSquares);
        this.backToMenu.draw(canvas);

        canvas.drawRect((600f / 1080) * GameView.WIDTH, (1325f / 1701) * GameView.HEIGHT, (900f / 1080) * GameView.WIDTH, (1520f / 1701) * GameView.HEIGHT, paintSquares);
        this.retryGame.draw(canvas);

        if (waitTime > 0){
            waitTime--;
        }
        //Log.i("waitTime",Integer.toString(waitTime));
    }

    public int getWaitTime(){
        return waitTime;
    }

    public void setWaitTime(int time){
        waitTime = time;
    }
}
