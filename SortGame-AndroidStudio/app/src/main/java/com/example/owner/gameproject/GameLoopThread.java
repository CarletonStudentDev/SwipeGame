package com.example.owner.gameproject;
import android.graphics.Canvas;


public class GameLoopThread extends Thread
{
    private GameView view;
    private boolean running;


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
        Canvas canvas;

        while (running)
        {
            canvas = null;

            try
            {
                canvas = view.getHolder().lockCanvas();


                synchronized (view.getHolder())
                {
                    view.draw(canvas);
                }
            }

            finally
            {
                if (canvas != null)
                    view.getHolder().unlockCanvasAndPost(canvas);

            }

        }
    }
}
