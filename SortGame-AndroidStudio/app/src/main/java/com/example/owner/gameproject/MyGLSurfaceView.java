package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    Context context;
    MyRenderer renderer;

    private float mPreviousX;
    private float mPreviousY;

    private int score;

    public MyGLSurfaceView(Context context) {
        super(context);
        this.context = context;

        renderer = new MyRenderer(context.getResources(),this);
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null)
        {

            float r = (float)getHeight() / getWidth();

            float x = event.getX();
            float y = event.getY();

            // convert touch coordinates into OpenGL coordinates
            float newX = (-(event.getX() * 2) / getWidth() + 1f) / r;
            float newY = -(event.getY() * 2) / getHeight() + 1f;
            //Log.i("x", Float.toString(newX));
            //Log.i("y", Float.toString(newY));
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if(renderer.gameBoard.blueTouched(newX, newY)){
                    //ASSUMING CORRECT
                    renderer.mBar.increaseNumFull();
                    renderer.score.addToScore(1);

                    renderer.blue++;
                    Log.i("blue", Integer.toString(renderer.blue));
                } else if (renderer.gameBoard.greenTouched(newX, newY)){
                    //ASSUMING MISTAKE
                    renderer.mBar.reset();
                    renderer.topBar.decreaseHearts();

                    renderer.green++;
                    Log.i("green", Integer.toString(renderer.green));
                } else if (renderer.gameBoard.purpleTouched(newX, newY)){
                    //ASSUMING MISTAKE
                    renderer.mBar.reset();
                    renderer.topBar.decreaseHearts();

                    renderer.purple++;
                    Log.i("purple", Integer.toString(renderer.purple));
                } else if (renderer.gameBoard.redTouched(newX, newY)){
                    //ASSUMING MISTAKE
                    renderer.mBar.reset();
                    renderer.topBar.decreaseHearts();

                    renderer.red++;
                    Log.i("red", Integer.toString(renderer.red));

                }
            }

            /*if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                float deltaX = (x - mPreviousX) / r / 2f;
                float deltaY = (y - mPreviousY) / r / 2f;

                if(renderer.card.inShape(newX, newY)){
                    renderer.mDeltaX += deltaX;
                    renderer.mDeltaY += deltaY;
                }

            }*/
            mPreviousX = x;
            mPreviousY = y;

            return true;
        }
        else
        {
            return super.onTouchEvent(event);
        }
    }
    public MyRenderer getRenderer(){
        return renderer;
    }
}
