/**
 * Created by Varun on 3/8/2015.
 */
package com.example.owner.gameproject;
import android.os.CountDownTimer;

public class GameClock {
    private long remainingTimeLeft;
    private CountDownTimer timer;
    public static long millisInterval = 1000;

    public GameClock(long TimeInMillis){
        this.createNewTimer(TimeInMillis);
    }

    //Method to add time to the clock.
    public void addTime(long addedTimeInMillis) {
        createNewTimer(remainingTimeLeft + addedTimeInMillis);
    }

    private void createNewTimer(long timeInMillis) {
        if(timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(timeInMillis, millisInterval) {
            // NOTE: This part is a little messy in implementation there is probably a way around it to make the code nicer.
            //Override of the onTick and Onfinish of the new instance of CountDownTime created.
            @Override
            public void onTick(final long millisUntilFinished) {
                remainingTimeLeft = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                // do something here
            }
            //Start the new instance of CountDownTimer.
        }.start();

    }
}