package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by home on 06/11/2014.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private MyRenderer renderer = new MyRenderer(this);

    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        
        return true;
    }
}
