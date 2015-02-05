package Model;

import android.content.Context;

import DrawableObjects.Card;
import DrawableObjects.GameBoard;
import DrawableObjects.GameOverScreen;
import DrawableObjects.MultiplierBar;
import DrawableObjects.Numbers;
import DrawableObjects.TopBar;

/**
 * GameSetup sets up the SwipeGame.
 *
 * @author Jeton Sinoimeri
 * @version 1.3
 * @since 2014-01-15
 *
 */

public class GameSetup
{
    /**
     * drawableTimer: Drawable instance representing the DrawableObjects drawableTimer class.
     *
     */

    private Numbers drawableTimer;

    /**
     * multiplierBar: MultiplierBar instance representing the DrawableObjects MultiplierBar class.
     *
     */

    private MultiplierBar multiplierBar;


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
     * score: Score instance representing the DrawableObjects Score class.
     *
     */

    private Numbers score;


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
     * player: Player instance representing the Model Player class.
     *
     */

    private Player player;


    /**
     * multiplier: Multiplier instance representing the Model Multiplier class.
     *
     */

    private Multiplier multiplier;


    /**
     * clock: Clock instance representing the Model Clock class.
     *
     */

    private Clock clock;


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

    /**
     * gameOverScreen: variable representing the gameOverScreen object.
     *
     */

    private GameOverScreen gameOverScreen;

    /**
     * Constructor for the GameSetup class.
     *
     * @param context: Context instance representing the Context of the app.
     *
     * @param gameTime: long value representing the total game time.
     *
     */

    public GameSetup(Context context, long gameTime)
    {
        this.context = context;
        this.clock = new Clock(gameTime);

        this.player = new Player();
        this.multiplier = new Multiplier();

        this.game = new Game(this.player, this.multiplier);
        this.cardGenerator = new CardGenerator();

        this.card = this.cardGenerator.generateCard(this.context);
        this.gameBoard = new GameBoard(this.context);

        this.topBar = new TopBar(this.context,-0.3f,0.9f);
        this.topBar.setFullHearts(this.player.getLives());

        this.multiplierBar = new MultiplierBar(this.context, 0.0f, 0.7f);
        this.score = new Numbers(context,-0.15f,0.9f,0,8,0,0.12f,0.1f,2);

        this.drawableTimer = new Numbers(context,-0.075f,0.45f,10,2,2,0.175f,0.13f,1);
        this.vibrate = new Vibrate(this.context);

        this.gameOverScreen = new GameOverScreen(this.context);
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
     * Getter for the game.
     *
     * @return game: Game instance representing the Model Game class.
     *
     */

    public Game getGame()
    {
        return this.game;
    }


    /**
     * Getter for the card generator.
     *
     * @return cardGenerator: CardGenerator instance representing
     *                        the Model CardGenerator class.
     *
     */

    public CardGenerator getCardGenerator()
    {
        return this.cardGenerator;
    }


    /**
     * Getter for the player.
     *
     * @return player: Player instance representing the Model
     *                 Player class.
     *
     */

    public Player getPlayer()
    {
        return this.player;
    }


    /**
     * Getter for the multiplier.
     *
     * @return multiplier: Multiplier instance representing the Model
     *                     Multiplier class.
     *
     */

    public Multiplier getMultiplier()
    {
        return this.multiplier;
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
     * Getter for the context.
     *
     * @return context: Context instance representing the Context
     *                  of the app.
     *
     */

    public Context getContext()
    {
        return this.context;
    }


    /**
     * Getter for the Drawable clock.
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
     * Getter for the vibrate.
     *
     * @return vibrate: Vibrate instance representing the Android Vibrator feature.
     *
     */

    public Vibrate getVibrate ()
    {
        return this.vibrate;
    }

    /**
     * Getter for the gameOverScreen
     *
     */

    public GameOverScreen getGameOverScreen ()
    {
        return this.gameOverScreen;
    }
}
