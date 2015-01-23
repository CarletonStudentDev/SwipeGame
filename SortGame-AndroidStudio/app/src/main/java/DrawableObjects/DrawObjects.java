package DrawableObjects;


import Model.GameTouchLogic;


/**
 * Draws objects to the Screen.
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-01-15
 *
 */

public class DrawObjects
{

    /**
     * Calls the fields draw methods.
     *
     * @param gameTouchLogic: GameTouchLogic instance representing the logic of
     *                        the game and the touch events.
     * @param mMVPMatrix: float [] representing the perspective projection of the
     *                    object on the screen.
     * @param scratch: float [] representing the perspective projection of the
     *                 object on the screen.
     *
     */

    public static void draw(GameTouchLogic gameTouchLogic, float [] mMVPMatrix, float [] scratch)
    {
        gameTouchLogic.getTopBar().draw(mMVPMatrix);
        gameTouchLogic.getMultiplierBar().draw(mMVPMatrix);
        gameTouchLogic.getGameBoard().draw(mMVPMatrix);
        gameTouchLogic.getCard().draw(scratch);
        gameTouchLogic.getScore().draw(mMVPMatrix);
    }


}
