package Model;

/**
 * Timer counts down from an initial time
 * to zero in intervals of milliseconds
 *
 * @author Jeton Sinoimeri
 * @version 1.4
 * @since 2014-11-10
 *
 */

public class Timer
{

    private long startTime,
                 maxTime,
                 currentTime;

    public Timer(long maxTime)
    {
        this.startTime = System.currentTimeMillis();
        this.maxTime = maxTime;
        this.currentTime = startTime;
    }


    public boolean timeOut()
    {
        return System.currentTimeMillis() - this.startTime > this.maxTime;
    }

    public void resetTimer() {startTime = System.currentTimeMillis();}

    public long getTime()
    {
        return System.currentTimeMillis() - startTime;
    }
}
