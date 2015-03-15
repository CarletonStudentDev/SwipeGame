package com.example.owner.gameproject;
import android.graphics.Canvas;


public class GameLoopThread extends Thread
{
    private static final long FPS = 60;
    private GameView view;
    private boolean running;

    public static Canvas canvas;

    public GameLoopThread(GameView view)
    {
        this.running = false;
        this.view = view;
    }

    public void setRunning(boolean run)
    {
        running = run;
    }

    @Override
    public void run()
    {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        Canvas c = null;

        while (running)
        {
            startTime = System.currentTimeMillis();
            try
            {
                c = view.getHolder().lockCanvas();
                canvas = c;

                synchronized (view.getHolder())
                {
                    view.draw(c);
                }
            }

            finally
            {
                if (c != null)
                    view.getHolder().unlockCanvasAndPost(c);

            }

            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try
            {
                if (sleepTime > 0)
                    sleep(sleepTime);

                else
                    sleep(10);
            }

            catch (Exception e) { }
        }
    }
}
