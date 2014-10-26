package com.example.owner.gameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by home on 26/10/2014.
 */
public class Game {

    private SurfaceView view;
    private int xTouch;
    private int yTouch;
    private boolean touching = false;

    private Bitmap bmp;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 1;

    public Game(SurfaceView view){
        this.view = view;
        bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.ic_launcher);
    }

    // do the logic here
    private void update(){
        if (touching) {
            x = xTouch;
            y = yTouch;
        }

        if (x == view.getWidth() - bmp.getWidth()) {
            xSpeed = -1;
        }
        if (x == 0) {
            xSpeed = 1;
        }
        x = x + xSpeed;
    }

    // do the drawing here
    public void onDraw(Canvas c){
        update();
        c.drawBitmap(bmp,x,y,null);
    }

    // called in the GameView onTouchListener to find where the touch was made
    public void setTouch(int x, int y){
        xTouch = x;
        yTouch = y;
    }
    public void setTouching(boolean a){
        touching = a;
    }

}
