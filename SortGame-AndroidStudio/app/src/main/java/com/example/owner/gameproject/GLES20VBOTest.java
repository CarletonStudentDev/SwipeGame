package com.example.owner.gameproject;

/*
Note: these not exist or not work before Android 2.3

GLES20.glVertexAttribPointer
GLES20.glDrawElements
 */

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import OpenGLEngine.GDC11Renderer;


public class GLES20VBOTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(2);
        view.setRenderer(new GDC11Renderer(getApplicationContext()));

        setContentView(view);
    }

}










