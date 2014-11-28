package Model;

import android.os.CountDownTimer;

/**
 * Timer counts down from an initial time
 * to zero in intervals of milliseconds
 *
 * @see android.os.CountDownTimer
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-11-10
 *
 */

public class Timer extends CountDownTimer
{

    /**
     * Long constant representing the interval in which
     * the time will be decreased by.
     *
     */

    private static final Long INTERVAL = 1000L;




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
        super(millisecondsInitTime, INTERVAL);
        this.game = game;
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

}
