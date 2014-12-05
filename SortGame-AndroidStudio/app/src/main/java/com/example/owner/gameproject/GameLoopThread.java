package com.example.owner.gameproject;

public class GameLoopThread extends Thread {
    /*
    static final long FPS = 10;

    private MyGLSurfaceView view;
    private boolean running = false;

    public GameLoopThread(MyGLSurfaceView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            MyRenderer r = null;
            startTime = System.currentTimeMillis();
            try {
                r = view.getRenderer();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
    */
}