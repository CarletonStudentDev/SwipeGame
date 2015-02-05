package Model;

/**
 * Clock counts down from an initial time
 * to zero in intervals of milliseconds
 *
 * @author Jeton Sinoimeri
 * @version 1.4
 * @since 2014-11-10
 *
 */

public class Clock
{

    private long startTime,
                 maxTime,
                 currentTime;
    private boolean stopClock;

    public Clock(long maxTime)
    {
        this.startTime = System.currentTimeMillis();
        this.maxTime = maxTime;
        this.currentTime = startTime;
        this.stopClock = false;
    }


    public boolean timeOut()
    {
        return timePassed() >= this.maxTime;
    }

    public void resetTimer() {startTime = System.currentTimeMillis();}

    public long timePassed()
    {
        if(stopClock == false){
            return System.currentTimeMillis() - startTime;
        }else{
            return maxTime;
        }
    }

    public void stopClock(){
        this.stopClock = true;
    }

    public long getMaxTime(){
        return maxTime;
    }

}
