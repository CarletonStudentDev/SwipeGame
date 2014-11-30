package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements Renderer {

    private Card card;
    private GLSurfaceView view;

    private Circle circle;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Context context;
    public MyRenderer(Context context, GLSurfaceView view){
        this.view = view;
        this.context = context;
    }

    public boolean onTouchEvent(MotionEvent event){

        float ratio = (float) view.getHeight() / view.getWidth();

        // convert touch coordinates into OpenGL coordinates
        float x = (-(event.getX() * 2) / view.getWidth() + 1f) / ratio;
        float y = -(event.getY() * 2) / view.getHeight() + 1f;

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                card.move(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                card.move(x,y);
                break;
            case MotionEvent.ACTION_UP:
                card.move(x,y);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 171f/255f, 34f/255f, 52f/255f, 1.0f );

        int color = context.getResources().getColor(R.color.lightRed);

        card = new Card(context, mMVPMatrix, R.drawable.red, 0.0f, 0.0f);
        circle = new Circle(context, 0.1f, 0, 0, color);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public void update(){

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        update();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        card.draw();
        circle.draw(mMVPMatrix);
    }
}