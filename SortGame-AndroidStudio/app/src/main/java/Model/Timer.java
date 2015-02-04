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
    private boolean stopTimer;

    public Timer(long maxTime)
    {
        this.startTime = System.currentTimeMillis();
        this.maxTime = maxTime;
        this.currentTime = startTime;
        this.stopTimer = false;
    }


    public boolean timeOut()
    {
        if(timePassed() >= this.maxTime){
            return true;
        }else{
            return false;
        }
    }

    public void resetTimer() {startTime = System.currentTimeMillis();}

    public long timePassed()
    {
        if(stopTimer == false){
            return System.currentTimeMillis() - startTime;
        }else{
            return maxTime;
        }
    }

    public void stopTimer(){
        this.stopTimer = true;
    }

    public long getMaxTime(){
        return maxTime;
    }

}
