package Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;

import com.example.owner.gameproject.MyActivity;
import com.example.owner.gameproject.R;
import com.example.owner.gameproject.StartNormalActivity;

import DrawableObjects.Card;
import DrawableObjects.GameBoard;
import DrawableObjects.GameOverScreen;
import DrawableObjects.MultiplierBar;
import DrawableObjects.Numbers;
import DrawableObjects.TopBar;


/**
 * GameTouchLogic contains all the logic of
 * the SwipeGame.
 *
 * @author Jeton Sinoimeri
 * @version 1.4
 * @since 2014-01-15
 *
 */

public class GameTouchLogic
{

    private GameOverScreen gameOverScreen;
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
     * clock: Clock instance representing the Model Clock class.
     *
     */

    private Clock clock;


    /**
     * drawableTimer: Numbers instance representing the Drawable
     *                Clock.
     *
     */

    private Numbers drawableTimer;


    /**
     * multiplierBar: MultiplierBar instance representing the DrawableObjects MultiplierBar class.
     *
     */

    private MultiplierBar multiplierBar;


    /**
     * score: Score instance representing the DrawableObjects Score class.
     *
     */

    private Numbers score;


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
     * context: Context instance representing the Context of the app.
     *
     */

    private Context context;


    /**
     * vibrate: Vibrate instance representing the Android Vibrator feature.
     *
     */

    private Vibrate vibrate;


    public SoundPool sounds;

    public int correctSound;

    public int wrongSound;

    public int beepSound;

    private Activity activity;
    private Intent intent;


    /**
     * Constructor for the GameTouchLogic class.
     *
     * @param view: View instance representing the android.view.View class.
     * @param gameSetup: GameSetup instance representing the setup of the
     *                   SwipeGame.
     *
     */

    public GameTouchLogic(View view, GameSetup gameSetup, Activity activity)
    {
        this.activity = activity;
        this.view = view;

        this.player = gameSetup.getPlayer();
        this.game = gameSetup.getGame();

        this.cardGenerator = gameSetup.getCardGenerator();
        this.clock = gameSetup.getClock();

        this.multiplierBar = gameSetup.getMultiplierBar();
        this.score = gameSetup.getScore();

        this.topBar = gameSetup.getTopBar();
        this.card = gameSetup.getCard();

        this.gameBoard = gameSetup.getGameBoard();
        this.context = gameSetup.getContext();

        this.drawableTimer = gameSetup.getDrawableTimer();
        this.vibrate = gameSetup.getVibrate();

        this.gameOverScreen = gameSetup.getGameOverScreen();

        if((android.os.Build.VERSION.SDK_INT) == 21){
            SoundPool.Builder builder =  new SoundPool.Builder();
            builder.setMaxStreams(10); //Assuming only 10 sounds will play simultaneously
            sounds = builder.build(); // Builder.build returns a new instance of SoundPool.
        }
        else{
            sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        }

        correctSound = sounds.load(context, R.raw.correct,1);
        wrongSound = sounds.load(context, R.raw.wrong,1);
        beepSound = sounds.load(context, R.raw.beep, 1);

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
                if (this.game.getGameOver())
                {
                    this.clock.stopClock();


                    if (this.gameBoard.getGameOverButton(newX, newY) == 2)
                        activity.finish();

                    else if (this.gameBoard.getGameOverButton(newX, newY) == 1)
                    {
                        activity.finish();
                        intent = new Intent(activity, StartNormalActivity.class);
                        activity.startActivity(intent);
                        
                    }

                }

                else
                {
                    if (this.gameBoard.getQuadrant(newX, newY) == this.card.getColorId())
                        this.correct();

                    else if (this.gameBoard.getQuadrant(newX, newY) != 0)
                        this.incorrect();

                }
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

        // need to multiply 100 by the multiplier score
        this.score.increase(100*this.multiplierBar.giveMulti());

        if(drawableTimer.getFullNumber() >= 0)
            this.drawableTimer.increase(1);


        this.card = this.cardGenerator.generateCard(this.context);

        //Play correct match sound
        playSound(correctSound, 2f);


    }


    /**
     * An incorrect match has been made.
     *
     */

    private void incorrect()
    {
        this.game.incorrectMatch();
        this.setLivesResetMBar();

        this.card = this.cardGenerator.generateCard(this.context);
        this.vibrate.vibrate();

        //Play wrong match sound
        playSound(wrongSound, 2f);

    }


    /**
     * The time has run out on the player.
     *
     */

    public void timeOut()
    {
        this.player.setCurrentScore(this.score.getFullNumber());
        this.game.timeOut();
    }


    /**
     * The player's lives have finished.
     *
     */

    public void livesFinished()
    {
        this.player.setCurrentScore(this.score.getFullNumber());
        this.game.livesFinish();
    }


    /**
     * Sets the lives of the top bar and resets the multiplier Bar.
     *
     */

    private void setLivesResetMBar()
    {
        this.multiplierBar.reset();
        this.topBar.decreaseHearts();
    }


    /**
     * Plays the corresponding sound depending on the correctness
     * of the match.
     *
     * @param soundId: integer value representing the sound to be played.
     * @param speed: float value representing the speed of the sound to be
     *               played.
     *
     */

    public void playSound (int soundId, float speed)
    {
        float volume = MyActivity.volume;
        sounds.play(soundId,volume,volume,0,0,speed);
    }


    /**
     * Getter for the multiplier bar.
     *
     * @return multiplierBar: MultiplierBar instance representing the
     *                        DrawableObjects MultiplierBar class.
     *
     */

    public MultiplierBar getMultiplierBar()
    {
        return this.multiplierBar;
    }


    /**
     * Getter for the top bar.
     *
     * @return topBar: TopBar instance representing the DrawableObjects
     *                 TopBar class.
     *
     */

    public TopBar getTopBar()
    {
        return this.topBar;
    }


    /**
     * Getter for the card.
     *
     * @return card: Card instance representing the DrawableObjects
     *               Card class.
     *
     */

    public Card getCard()
    {
        return this.card;
    }


    /**
     * Getter for the game board.
     *
     * @return gameBoard: GameBoard instance representing the
     *                    DrawableObjects GameBoard class.
     *
     */

    public GameBoard getGameBoard()
    {
        return this.gameBoard;
    }


    /**
     * Getter for the score.
     *
     * @return score: Score instance representing the DrawableObjects
     *                Score class.
     *
     */

    public Numbers getScore()
    {
        return this.score;
    }


    /**
     * Getter for the clock.
     *
     * @return clock: Clock instance representing the Model Clock class.
     *
     */

    public Clock getClock()
    {
        return this.clock;
    }


    /**
     * Getter for the drawable clock.
     *
     * @return drawableTimer: Numbers instance representing the Drawable
     *                        Clock.
     *
     */

    public Numbers getDrawableTimer()
    {
        return this.drawableTimer;
    }


    /**
     * Getter for the gameOverScreen
     *
     */

    public GameOverScreen getGameOverScreen ()
    {
        return this.gameOverScreen;
    }


    /**
     * Getter for the game
     *
     */

    public Game getGame()
    {
        return this.game;
    }


    /**
     * Getter for the player.
     *
     * @return player: Player instance representing the player
     *                 the game.
     *
     */

    public Player getPlayer()
    {
        return this.player;
    }

}
