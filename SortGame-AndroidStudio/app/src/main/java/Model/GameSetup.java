package Model;

import android.content.res.Resources;

import DrawableObjects.Card;
import DrawableObjects.GameBoard;
import DrawableObjects.MultiplierBar;
import DrawableObjects.Score;
import DrawableObjects.TopBar;

/**
 * GameSetup sets up the SwipeGame.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-01-15
 *
 */

public class GameSetup
{

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

    private Score score;


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
     * timer: Timer instance representing the Model Timer class.
     *
     */

    private Timer timer;


    /**
     * resources: Resources instance representing the Resources of the app.
     *
     */

    private Resources resources;



    /**
     * Constructor for the GameSetup class.
     *
     * @param resources: Resources instance representing the Resources of the app.
     *
     * @param gameTime: long value representing the total game time.
     *
     */

    public GameSetup(Resources resources, long gameTime)
    {
        this.resources = resources;
        this.timer = new Timer(gameTime);

        this.player = new Player();
        this.multiplier = new Multiplier();

        this.game = new Game(this.player, this.multiplier);
        this.cardGenerator = new CardGenerator();

        this.card = this.cardGenerator.generateCard(this.resources);
        this.gameBoard = new GameBoard(this.resources);

        this.topBar = new TopBar(resources,-0.3f,0.9f);
        this.topBar.setFullHearts(this.player.getLives());

        this.multiplierBar = new MultiplierBar(this.resources, 0.0f, 0.7f);
        this.score = new Score(this.resources,0,0.9f);

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

    public Score getScore()
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
     * Getter for the timer.
     *
     * @return timer: Timer instance representing the Model Timer class.
     *
     */

    public Timer getTimer()
    {
        return this.timer;
    }


    /**
     * Getter for the resources.
     *
     * @return resources: Resources instance representing the Resources
     *                    of the app.
     *
     */

    public Resources getResources()
    {
        return this.resources;
    }
}
