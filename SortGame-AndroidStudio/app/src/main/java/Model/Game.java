package Model;

/**
 * Game contains all the rules and information
 * about playing the card game.
 *
 * @author Jeton Sinoimeri
 * @version 1.6
 * @since 2014-11-10
 *
 */

public class Game
{

    /**
     * deck: Listener reference representing the deck of cards
     *       required to play the game.
     *
     * player: Listener reference representing the user required
     *         to play the game.
     *
     * multiplier: Listener reference representing the score
     *             multiplier the player reference requires
     *             to update the score.
     *
     */

    private Listener player,
                     multiplier;



    /**
     * timedOut: boolean value representing if the Clock
     *           triggered an interrupt indicating that
     *           the time ran out.
     *
     */

    private boolean timedOut;
    private boolean gameOver;


    /**
     * Constructor for the Game class
     *
     * @param player: Listener reference representing the user required
     *                to play the game.
     *
     * @param multiplier: Listener reference representing the score
     *                    multiplier the player reference requires
     *                    to update the score.
     *
     */

    public Game(Listener player, Listener multiplier)
    {
        this.player = player;
        this.multiplier = multiplier;
        this.timedOut = false;
        this.gameOver = false;
    }


    /**
     * Getter for the Multiplier.
     *
     * @return multiplier: Listener reference representing the Multiplier
     *
     */

    public Listener getMultiplierMeter()
    {
        return this.multiplier;
    }


    /**
     * Getter for the Player.
     *
     * @return player: Listener reference representing the Player
     *
     */

    public Listener getPlayer()
    {
        return this.player;
    }




    /**
     * Getter for the timed out variable.
     *
     * @return timedOut: boolean value representing if the Clock
     *                   triggered an interrupt indicating that
     *                   the time ran out.
     *
     */

    public boolean getTimedOut()
    {
        return this.timedOut;
    }


    /**
     * Mutator for the timed out variable.
     *
     * @param timedOut: boolean value representing if the Clock
     *                  triggered an interrupt indicating that
     *                  the time ran out.
     */

    public void setTimedOut(boolean timedOut) { this.timedOut = timedOut; }


    /**
     * Notifies the Listeners when the Clock has reached
     * zero.
     *
     */

    public void timeOut()
    {
        GameEvent ge = new GameEvent(this);

        //this.setTimedOut(true);
        gameOver = true;

        this.player.timeOut(ge);
    }


    /**
     * Notifies the Listeners when there is a correct
     * match.
     *
     */

    public void correctMatch()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.correctMatch(ge);
        this.player.correctMatch(ge);
     }

    /**
     * Getter for the game over variable.
     *
     * @return gameOver: boolean value representing if the Player
     *                   triggered the game over.
     *
     */

    public boolean getGameOver()
    {
        return this.gameOver;
    }

    /**
     * Notifies the Listeners when there is an
     * incorrect match.
     *
     */

    public void incorrectMatch()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.incorrectMatch(ge);
        this.player.incorrectMatch(ge);
        Player p = (Player) this.getPlayer();
        if(p.getLives()==0){
            //Set GameOver
            this.gameOver = true;
        }

    }


    /**
     * Notifies the Listeners when the number of
     * lives finish.
     *
     */

    public void livesFinish()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.livesFinish(ge);
        this.player.livesFinish(ge);

    }

}
