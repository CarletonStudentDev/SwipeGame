package com.example.owner.gameproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * GameView class represents the application view.
 *
 * This is a subclass of SurfaceView and implements
 * the interface SurfaceHolder.Callback
 *
 * @see android.view.SurfaceView
 * @see android.view.SurfaceHolder.Callback
 *
 * @author Robert
 * @version 1.2
 * @since 2015-02-27
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;

    public static GameView instance;
    public static float centerX;
    public static float centerY;
    public static float scale;

    private GameManager gameManager;

    public static Typeface typeface;

    public GameView(Activity activity)
    {
        super(activity);

        typeface = Typeface.createFromAsset(activity.getAssets(), "fonts/calibri.ttf");

        holder = getHolder();
        holder.addCallback(this);

        instance = this;

        gameLoopThread = new GameLoopThread(this);
        this.gameManager = new GameManager(this);

        gameLoopThread.setGameManager(gameManager);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }


    /**
     * @see SurfaceView
     * @param canvas Canvas instance representing android.graphics.Canvas
     */

    @Override
    public void draw(Canvas canvas)
    {
        if (canvas != null)
        {
            canvas.drawColor(getResources().getColor(R.color.white));

            if (x < getWidth() - bmp.getWidth())
                x++;

            canvas.drawBitmap(bmp, x, 10, null);

            gameManager.draw(canvas);

        }
    }

    /**
     * Checks whether or not an motion event has occurred. It is
     * also responsible for determining a correct, incorrect match or
     * a time out has occurred depending on the motion event.
     *
     * @see android.view.MotionEvent;
     *
     * @param event MotionEvent instance representing the
     * motion of event that has occurred.
     *
     * @return boolean representing whether or not an event has occurred.
     */

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return gameManager.onTouchEvent(event);
    }


    /**
     * @see SurfaceHolder.Callback
     * @param holder The SurfaceHolder whose surface is being created.
     */

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    /**
     * @see SurfaceHolder.Callback
     * @param holder The SurfaceHolder whose surface is being created.
     * @param format The new PixelFormat of the surface.
     * @param width The new width of the surface.
     * @param height The new height of the surface.
     */

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    /**
     * @see SurfaceHolder.Callback
     * @param holder The SurfaceHolder whose surface is being created.
     */

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        gameLoopThread.setRunning(false);
    }

}
