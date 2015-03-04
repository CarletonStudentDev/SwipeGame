package DrawableObjects;

import com.example.owner.gameproject.R;

import Model.GameTouchLogic;
import AndroidServices.MediaSounds;


/**
 * Draws objects to the Screen.
 *
 * @author Jeton Sinoimeri
 * @version 1.6
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
     *
     */

    public static void draw(GameTouchLogic gameTouchLogic, float [] mMVPMatrix)
    {
        gameTouchLogic.getTopBar().draw(mMVPMatrix);
        gameTouchLogic.getMultiplierBar().draw(mMVPMatrix);
        gameTouchLogic.getGameBoard().draw(mMVPMatrix);
        gameTouchLogic.getCard().draw(mMVPMatrix);
        gameTouchLogic.getTextManager().draw(mMVPMatrix);

        if(gameTouchLogic.getTopBar().getFullHearts() == 0)
        {
            gameTouchLogic.getGameOverScreen().setOutOfLives(true);
            gameTouchLogic.livesFinished();
        }

        else if(gameTouchLogic.getDrawableTimer().getFullNumber() >= 0)
        {
            if (gameTouchLogic.getDrawableTimer().getFullNumber() == 0)
            {
                gameTouchLogic.getGameOverScreen().setTimedOut(true);
                gameTouchLogic.timeOut();
            }

            else if (gameTouchLogic.getClock().timePassed() >= 1000)
            {

                if (gameTouchLogic.getDrawableTimer().getFullNumber() <= 4 && gameTouchLogic.getDrawableTimer().getFullNumber() != 1)
                    MediaSounds.loadPlaySound(gameTouchLogic.getContext(), R.raw.beep, 1, 2f);

                gameTouchLogic.getDrawableTimer().decrease(1);
                gameTouchLogic.getClock().resetTimer();

            }

            gameTouchLogic.getDrawableTimer().draw(mMVPMatrix);
        }


        if(gameTouchLogic.getGame().getGameOver())
        {
            gameTouchLogic.getGameOverScreen().updateScore(gameTouchLogic.getPlayer().getCurrentScore());
            gameTouchLogic.getGameOverScreen().updateHighScore(gameTouchLogic.getPlayer().getHighScore());
            gameTouchLogic.getGameOverScreen().draw(mMVPMatrix);
        }

    }


}
