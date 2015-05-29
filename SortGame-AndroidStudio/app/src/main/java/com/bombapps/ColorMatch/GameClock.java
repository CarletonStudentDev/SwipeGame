
package com.bombapps.ColorMatch;
import android.os.CountDownTimer;

import java.util.Observable;

/**
 * @author Varun Sriram
 * @version 1.0
 * @since 2015-03-08
 *
 */
public class GameClock extends Observable
{
    private long remainingTimeLeft;
    private CountDownTimer timer;
    public static long millisInterval = 950L;
    public int secondsPassed;

    public GameClock(long TimeInMillis)
    {
        secondsPassed = 0;
        remainingTimeLeft = TimeInMillis;
        this.createNewTimer(TimeInMillis);
    }

    public int getTimePassed(){

        return secondsPassed;
    }
    public int getRemainingTimeLeft()
    {
        if(remainingTimeLeft<2*millisInterval){
            return 1;
        }else if(remainingTimeLeft<=millisInterval+100L){
            return 0;
        }
        return (int)remainingTimeLeft / (int)millisInterval;
    }

    //Method to add time to the clock.
    public void addTime(long addedTimeInMillis)
    {
        createNewTimer(remainingTimeLeft + addedTimeInMillis);
    }


    public void stopTime()
    {
        timer.cancel();
    }

    private void createNewTimer(long timeInMillis)
    {

        if(timer != null)
            timer.cancel();

        timer = new CountDownTimer(timeInMillis, millisInterval)
        {
            // NOTE: This part is a little messy in implementation there is probably a way around it to make the code nicer.

            boolean finished = false;
            //Override of the onTick and Onfinish of the new instance of CountDownTime created.
            @Override
            public void onTick(final long millisUntilFinished)
            {
                remainingTimeLeft = millisUntilFinished;
                secondsPassed++;
            }

            @Override
            public void onFinish()
            {
                finished = true;
                secondsPassed++;
                remainingTimeLeft = 0;
                // do something here
                setChanged();
                notifyObservers(finished);
            }
            //Start the new instance of CountDownTimer.
        }.start();

    }
}