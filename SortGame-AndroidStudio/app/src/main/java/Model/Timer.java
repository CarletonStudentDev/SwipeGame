package Model;

/**
 * Timer class keeps track of the amount of time
 * passed between the start of the game and the
 * current System time by checking the maximum
 * allowed game time.
 *
 * @author Jeton Sinoimeri
 * @version 1.5
 * @since 2014-11-10
 *
 */

public class Timer
{

    /**
     * startTime: long value representing the System time
     *            the game started.
     *
     * maxTime: long value representing the maximum amount
     *          of time the game will last.
     *
     */

    private long startTime,
                 maxTime;



    /**
     * Constructor for the Timer class.
     *
     * @param maxTime: long value representing the maximum amount
     *                 of time the game will last.
     *
     */

    public Timer(long maxTime)
    {
        this.startTime = System.currentTimeMillis();
        this.maxTime = maxTime;
    }


    /**
     * Checks whether or not the difference between the start and current
     * times have exceeded the maximum game time.
     *
     * @return bool: boolean value representing whether the difference
     *               in the current and start System times have exceeded
     *               the maximum game time.
     *
     */

    public boolean timeOut()
    {
        return System.currentTimeMillis() - this.startTime > this.maxTime;
    }


    /**
     * Resets the Timer to the current System time.
     *
     */

    public void resetTimer()
    {
        this.startTime = System.currentTimeMillis();
    }


}
