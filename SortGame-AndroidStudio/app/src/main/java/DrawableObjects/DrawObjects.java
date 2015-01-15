package DrawableObjects;


import Model.GameSetup;


/**
 * Draws objects to the Screen.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-01-15
 *
 */

public class DrawObjects
{

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
     * Constructor for the DrawObjects class.
     *
     * @param gameSetup: GameSetup instance representing the setup of the
     *                   SwipeGame.
     *
     */

    public DrawObjects(GameSetup gameSetup)
    {
        this.card = gameSetup.getCard();
        this.gameBoard = gameSetup.getGameBoard();
        this.multiplierBar = gameSetup.getMultiplierBar();
        this.score = gameSetup.getScore();
        this.topBar = gameSetup.getTopBar();
    }


    /**
     * Calls the fields draw methods.
     *
     * @param mMVPMatrix: float [] representing the perspective projection of the
     *                    object on the screen.
     * @param scratch: float [] representing the perspective projection of the
     *                 object on the screen.
     *
     */

    public void draw(float [] mMVPMatrix, float [] scratch)
    {
        topBar.draw(mMVPMatrix);
        multiplierBar.draw(mMVPMatrix);
        gameBoard.draw(mMVPMatrix);
        card.draw(scratch);
        score.draw(mMVPMatrix);
    }


}
