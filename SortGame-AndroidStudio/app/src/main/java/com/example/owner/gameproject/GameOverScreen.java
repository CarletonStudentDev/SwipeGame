package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

/**
 * GameOverScreen class displays the reason for the loss
 * of the game, the current score of the game, the highest
 * achieved score since first playing the game, and options
 * to either go back to main menu or to play again.
 *
 * @author Jeton Sinoimeri
 * @version 1.2
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
                                HIGHSCORESTRING = "High Score: ";


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
                       retryGame;


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
                  bottomCoordinate;


    /**
     * paint Paint instance representing the color, and font of the TextObject.
     *
     */

    private Paint paint, paintSquares;


    /**
     * Constructor for the GameOverScreen class.
     *
     * @param view View instance representing the view of the application.
     * @param typeface Typeface object representing the font type of the text.
     * @param textColor integer value obtained from getResources() representing
     *                  the color of the text.
     *
     */

    public GameOverScreen(View view, Typeface typeface, int textColor)
    {
        this.lossReason = new TextObject("", (540f/1080)*GameView.WIDTH, (325f/1701)*GameView.HEIGHT, typeface, textColor, (150f/1080) * GameView.WIDTH);
        this.score = new TextObject("Score:", (540f/1080)*GameView.WIDTH, (500f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.scoreNum = new TextObject(SCORESTRING, (540f/1080)*GameView.WIDTH, (650f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.highScore = new TextObject("High Score:", (540f/1080)*GameView.WIDTH, (800f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.highScoreNum = new TextObject(HIGHSCORESTRING, (540f/1080)*GameView.WIDTH, (950f/1701)*GameView.HEIGHT, typeface, textColor , (150f/1080) * GameView.WIDTH);
        this.backToMenu = new TextObject("Back", (350f/1080)*GameView.WIDTH, (1400f/1701)*GameView.HEIGHT, typeface, textColor, (100f/1080) * GameView.WIDTH);
        this.retryGame = new TextObject("Retry", (750f/1080)*GameView.WIDTH, (1400f/1701)*GameView.HEIGHT, typeface, textColor, (100f/1080) * GameView.WIDTH);

        this.leftCoordinate = 0.1f * view.getWidth();
        this.rightCoordinate = 0.9f * view.getWidth();
        this.topCoordinate = 0.1f * view.getHeight();
        this.bottomCoordinate =  0.9f * view.getHeight();

        this.paint = new Paint();
        this.paint.setColor(ColorsLoader.loadColorByName("darkBlue"));

        this.paintSquares=new Paint();
        this.paintSquares.setColor(ColorsLoader.loadColorByName("blue"));



    }


    /**
     * Setter for the score and highScore of the game to be displayed on screen.
     *
     * @param score long value representing the current score of the game.
     * @param highScore long value representing the highest score achieved
     *                  since playing the game.
     *
     */

    public void setScores(long score, long highScore)
    {
        this.scoreNum.setText("" + score);
        this.highScoreNum.setText("" + highScore);
    }


    /**
     * Getter for the game over buttons.
     *
     * @param xCoordinate float value representing the x-coordinate in pixels.
     * @param yCoordinate float value representing the y-coordinate in pixels.
     * @return integer representing the corresponding game over button.
     */

    public int getGameOverButton(float xCoordinate, float yCoordinate)
    {

        if( xCoordinate >= (200f/1080)*GameView.WIDTH && xCoordinate <= (500f/1080)*GameView.WIDTH
                && yCoordinate>=(1275f/1701)*GameView.HEIGHT && yCoordinate<=(1470f/1701)*GameView.HEIGHT){
            return 1;
        } else if(xCoordinate >=(600f/1080)*GameView.WIDTH && xCoordinate <= (900f/1080)*GameView.WIDTH
            && yCoordinate>=(1275f/1701)*GameView.HEIGHT && yCoordinate<=(1470f/1701)*GameView.HEIGHT){
            return 2;
        }else{
            return 0;
        }

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

    public void draw(Canvas canvas)
    {
        canvas.drawRect(leftCoordinate, topCoordinate, rightCoordinate, bottomCoordinate, paint);
        this.lossReason.draw(canvas);
        this.score.draw(canvas);

        this.scoreNum.draw(canvas);

        this.highScore.draw(canvas);
        this.highScoreNum.draw(canvas);

        canvas.drawRect((200f/1080)*GameView.WIDTH, (1275f/1701)*GameView.HEIGHT,(500f/1080)*GameView.WIDTH, (1470f/1701)*GameView.HEIGHT, paintSquares);
        this.backToMenu.draw(canvas);

        canvas.drawRect((600f/1080)*GameView.WIDTH, (1275f/1701)*GameView.HEIGHT,(900f/1080)*GameView.WIDTH, (1470f/1701)*GameView.HEIGHT, paintSquares);
        this.retryGame.draw(canvas);

    }
}
