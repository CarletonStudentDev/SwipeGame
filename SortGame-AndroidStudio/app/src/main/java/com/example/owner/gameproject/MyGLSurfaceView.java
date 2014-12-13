package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;

public class MyGLSurfaceView extends GLSurfaceView {

    Context context;
    MyRenderer renderer;

    private float mPreviousX;
    private float mPreviousY;

    public MyGLSurfaceView(Context context) {
        super(context);
        this.context = context;

        renderer = new MyRenderer(context,this);
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

            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                float deltaX = (x - mPreviousX) / r / 2f;
                float deltaY = (y - mPreviousY) / r / 2f;

                /*if(renderer.card.inShape(newX, newY)){*/
                    renderer.mDeltaX += deltaX;
                    renderer.mDeltaY += deltaY;
                //}

            }
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