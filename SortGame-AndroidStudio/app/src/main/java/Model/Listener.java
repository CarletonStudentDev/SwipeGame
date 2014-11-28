package Model;

/**
 * Interface for the Listeners that Game class
 * can notify when an event has occurred.
 *
 * @author Jeton Sinoimeri
 * @version 1.2
 * @since 2014-11-10
 *
 */

public interface Listener
{

    /**
     * Listeners are notified when there is a correct match.
     *
     * @param ge: GameEvent object representing the event that
     *            occurred
     *
     */

    public void correctMatch(GameEvent ge);


    /**
     * Listeners are notified when there is an incorrect match.
     *
     * @param ge: GameEvent object representing the event that
     *            occurred.
     *
     */

    public void incorrectMatch(GameEvent ge);


    /**
     * Listeners are notified when the time has run out.
     *
     * @param ge: GameEvent object representing the event that
     *            occurred.
     *
     */

    public void timeOut(GameEvent ge);


    /**
     * Listeners are notified when the amount of Player's lives have
     * reached zero.
     *
     * @param ge: GameEvent object representing the event that
     *            occurred.
     *
     */

    public void livesFinish(GameEvent ge);

}
