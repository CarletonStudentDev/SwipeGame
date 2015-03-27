
package com.example.owner.gameproject;
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
    public static long millisInterval = 1000L;

    public GameClock(long TimeInMillis)
    {
        remainingTimeLeft = TimeInMillis;
        this.createNewTimer(TimeInMillis);
    }

    public long getRemainingTimeLeft()
    {
        return remainingTimeLeft / millisInterval;
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
                if (remainingTimeLeft / millisInterval < 4)
                    MediaSounds.loadPlaySound(R.raw.correct, 1, 1f);
            }

            @Override
            public void onFinish()
            {
                finished = true;
                // do something here
                setChanged();
                notifyObservers(finished);
            }
            //Start the new instance of CountDownTimer.
        }.start();

    }
}