package Model;

import android.os.CountDownTimer;

/**
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-11-10
 *
 */
public class Timer extends CountDownTimer
{
    private static final Long INTERVAL = 1000L;
    private long timerLeft;
    private CountDownTimer timer;

    private Game game;

    public Timer(long millisecondsInitTime, Game game)
    {
        super(millisecondsInitTime, INTERVAL);
        this.game = game;
    }

    @Override
    public void onFinish()
    {
        this.game.timeOut();
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        this.timerLeft = millisUntilFinished;

    }

    public void pause()
    {
        this.cancel();
    }

    public void resume()
    {
        this.timer = new Timer(this.timerLeft, game);
        this.timer.start();
    }

}
