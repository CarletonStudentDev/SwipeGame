package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Class description
 *
 * @author Robert
 * @version 1.2
 * @since 2015-02-27
 */

public class GameView extends SurfaceView
{
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;

    private Canvas canvas = null;
    public static GameView instance;
    public static float centerX;
    public static float centerY;
    public static float scale;

    private GameManager gameManager;

    public GameView(Context context)
    {
        super(context);

        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                gameLoopThread.setRunning(false);
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {

            }
        });

        instance = this;


        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        this.gameManager = new GameManager(this);
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (canvas != null)
        {
            canvas.drawColor(getResources().getColor(R.color.white));

            if (x < getWidth() - bmp.getWidth())
                x++;

            canvas.drawBitmap(bmp, x, 10, null);

            gameManager.draw();

        }
    }



}
