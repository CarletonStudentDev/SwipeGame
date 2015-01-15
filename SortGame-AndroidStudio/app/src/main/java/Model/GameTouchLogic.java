package Model;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;

import DrawableObjects.Card;
import DrawableObjects.GameBoard;
import DrawableObjects.MultiplierBar;
import DrawableObjects.Score;
import DrawableObjects.TopBar;


/**
 * GameTouchLogic contains all the logic of
 * the SwipeGame.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-01-15
 *
 */

public class GameTouchLogic
{

    /**
     * view: View instance representing the android.view.View class.
     *
     */

    private View view;


    /**
     * player: Player instance representing the Model Player class.
     *
     */

    private Player player;


    /**
     * game: Game instance representing the Model Game class.
     *
     */

    private Game game;


    /**
     * cardGenerator: CardGenerator instance representing the Model CardGenerator class.
     *
     */

    private CardGenerator cardGenerator;


    /**
     * timer: Timer instance representing the Model Timer class.
     *
     */

    private Timer timer;


    /**
     * multiplierBar: MultiplierBar instance representing the DrawableObjects MultiplierBar class.
     *
     */

    private MultiplierBar multiplierBar;


    /**
     * score: Score instance representing the DrawableObjects Score class.
     *
     */

    private Score score;


    /**
     * topBar: TopBar instance representing the DrawableObjects TopBar class.
     *
     */

    private TopBar topBar;


    /**
     * card: Card instance representing the DrawableObjects Card class.
     *
     */

    private Card card;


    /**
     * gameBoard: GameBoard instance representing the DrawableObjects GameBoard class.
     *
     */

    private GameBoard gameBoard;


    /**
     * resources: Resources instance representing the Resources of the app.
     *
     */

    private Resources resources;


    /**
     * Constructor for the GameTouchLogic class.
     *
     * @param view: View instance representing the android.view.View class.
     * @param gameSetup: GameSetup instance representing the setup of the
     *                   SwipeGame.
     *
     */

    public GameTouchLogic(View view, GameSetup gameSetup)
    {
        this.view = view;

        this.player = gameSetup.getPlayer();
        this.game = gameSetup.getGame();

        this.cardGenerator = gameSetup.getCardGenerator();
        this.timer = gameSetup.getTimer();

        this.multiplierBar = gameSetup.getMultiplierBar();
        this.score = gameSetup.getScore();

        this.topBar = gameSetup.getTopBar();
        this.card = gameSetup.getCard();

        this.gameBoard = gameSetup.getGameBoard();
        this.resources = gameSetup.getResources();

    }


    /**
     * Checks whether or not an motion event has occurred. It is
     * also responsible for determining a correct, incorrect match or
     * a time out has occurred depending on the motion event.
     *
     * @see android.view.MotionEvent;
     *
     *
     * @param event: MotionEvent instance representing the
     *               motion of event that has occurred.
     *
     * @return bool: boolean representing whether or not
     *               an event has occurred.
     *
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
                if (this.gameBoard.getQuadrant(newX, newY) == this.card.getColorId())
                    this.correct();

                else
                    this.incorrect();
            }

            /*if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                float deltaX = (x - mPreviousX) / r / 2f;
                float deltaY = (y - mPreviousY) / r / 2f;

                if(renderer.card.inShape(newX, newY)){
                    renderer.mDeltaX += deltaX;
                    renderer.mDeltaY += deltaY;
                }

            }*/
            //mPreviousX = x;
            //mPreviousY = y;

            return true;
        }


        else if (this.timer.timeOut())
        {
            this.timeOut();
            return true;
        }


        return false;
    }


    /**
     * A correct match has been made.
     *
     */

    private void correct()
    {
        this.game.correctMatch();
        this.multiplierBar.increaseNumFull();

        this.score.addToScore(100, this.multiplierBar.giveMulti());


        this.card = this.cardGenerator.generateCard(this.resources);
    }


    /**
     * An incorrect match has been made.
     *
     */

    private void incorrect()
    {
        this.game.incorrectMatch();
        this.setLivesResetMBar();

        this.card = this.cardGenerator.generateCard(this.resources);
    }


    /**
     * The time has run out on the player.
     *
     */

    private void timeOut()
    {
        this.game.timeOut();
        this.setLivesResetMBar();
        this.timer.resetTimer();
    }


    /**
     * Sets the lives of the top bar and resets the multiplier Bar.
     *
     */

    private void setLivesResetMBar()
    {
        this.multiplierBar.reset();
        this.topBar.setFullHearts(this.player.getLives());
    }


}
