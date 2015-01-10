package Model;

import android.os.CountDownTimer;

/**
 * Timer counts down from an initial time
 * to zero in intervals of milliseconds
 *
 * @see android.os.CountDownTimer
 *
 * @author Jeton Sinoimeri
 * @version 1.3
 * @since 2014-11-10
 *
 */

public class Timer extends CountDownTimer
{

    /**
     * TIMEINTERVAL: long value representing the time interval in
     *               milliseconds the CountDownTimer will use to
     *               decrement the time
     *
     */

    private static final long TIMEINTERVAL = 1000L;


    /**
     * Long instance representing the amount of time
     * left in the session.
     *
     */

    private long timeLeft;


    /**
     * CountDownTimer instance representing the timer class
     *
     */

    private CountDownTimer timer;


    /**
     * Game instance representing the game which is being
     * played.
     *
     */

    private Game game;


    /**
     * Constructor for the Timer class
     *
     * @param millisecondsInitTime: Long representing the initial time in milliseconds
     * @param game: Game instance representing the game which is being played
     *
     */

    public Timer(long millisecondsInitTime, Game game)
    {
        super(millisecondsInitTime, TIMEINTERVAL);
        this.game = game;
        this.game.setTimedOut(false);
    }


    /**
     * @see android.os.CountDownTimer
     *
     */

    @Override
    public void onFinish()
    {
        this.game.timeOut();
    }


    /**
     * @see android.os.CountDownTimer
     *
     * @param millisUntilFinished: Long representing the amount of time
     *                             left until the timer finishes in
     *                             milliseconds.
     *
     */

    @Override
    public void onTick(long millisUntilFinished)
    {
        this.timeLeft = millisUntilFinished;
    }


    /**
     * Cancels the countdown timer when pause is called
     *
     */

    public void pause()
    {
        this.cancel();
    }


    /**
     * Resumes the countdown timer using the previous
     * paused time as the starting value.
     *
     */

    public void resume()
    {
        this.timer = new Timer(this.timeLeft, this.game);
        this.timer.start();
    }


    /**
     * Getter for the time left
     *
     * @return timeLeft: long representing the time left
     *                   in the game
     *
     */

    public long getTimeLeft()
    {
        return timeLeft;
    }
}
