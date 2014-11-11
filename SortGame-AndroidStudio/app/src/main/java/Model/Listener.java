package Model;

/**
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-11-10
 *
 */

public interface Listener
{
    public void correctMatch(GameEvent ge);
    public void incorrectMatch(GameEvent ge);
    public void timeOut(GameEvent ge);
    public void livesFinish(GameEvent ge);
    public void roundsOver(GameEvent ge);

}
