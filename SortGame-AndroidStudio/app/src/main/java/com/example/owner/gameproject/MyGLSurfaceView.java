package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by home on 06/11/2014.
 */
public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(new MyRenderer());
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
