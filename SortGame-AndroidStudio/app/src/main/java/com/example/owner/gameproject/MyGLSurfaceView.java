package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    Context context;
    MyRenderer renderer;
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
        renderer.onTouchEvent(event);

        return true;
    }
}
