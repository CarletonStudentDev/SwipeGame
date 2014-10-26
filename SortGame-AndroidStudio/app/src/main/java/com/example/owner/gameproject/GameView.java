package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;

    private Game game;

    public GameView(Context context) {
        super(context);

        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        game = new Game(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        int eventAction = event.getAction();

        switch (eventAction) {

            case MotionEvent.ACTION_DOWN:

                game.setTouch(x,y);
                game.setTouching(true);
                break;

            case MotionEvent.ACTION_MOVE:
                game.setTouch(x,y);
                game.setTouching(true);
                break;

            case MotionEvent.ACTION_UP:
                game.setTouching(false);
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.mediumRed));
        game.onDraw(canvas);
    }
}