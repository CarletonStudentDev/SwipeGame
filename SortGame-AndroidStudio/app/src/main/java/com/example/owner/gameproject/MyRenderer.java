package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements Renderer {

    public Card card;
    private GLSurfaceView view;
    private MultiplierBar mBar;

    private Circle circle;
    private Circle circle2;
    private Circle circle3;
    private Circle circle4;

    private float ratio;

    private Square square;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Context context;

    public float mDeltaX;
    public float mDeltaY;

    private float mPreviousX;
    private float mPreviousY;

    public MyRenderer(Context context, GLSurfaceView view){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );
        this.ratio = view.getWidth() / view.getHeight();

        int color = context.getResources().getColor(R.color.darkBlue);
        square = new Square(context, 0.65f * 2 , 0.2f, 0f, 0.9f, color);


        card = new Card(context, R.drawable.caution, 0.0f, -0.2f);
        //circle = new Circle(context, 0.5f, 0, 0, color);
        circle = new Circle(context, 0.35f, 0.4f, 0.35f, context.getResources().getColor(R.color.red));
        circle2 = new Circle(context, 0.35f, -0.4f, 0.35f, context.getResources().getColor(R.color.green));
        circle3 = new Circle(context, 0.35f, -0.4f, -0.75f, context.getResources().getColor(R.color.purple));
        circle4 = new Circle(context, 0.35f, 0.4f, -0.75f, context.getResources().getColor(R.color.blue));
        mBar = new MultiplierBar(context, 0.0f, 0.7f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        ratio = (float) width / height;

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

        float[] scratch = new float[16];

        Matrix.translateM(card.image.mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, card.image.mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;

        //square.draw(scratch);
        card.draw(scratch);
        square.draw(mMVPMatrix);
        circle.draw(mMVPMatrix);
        circle2.draw(mMVPMatrix);
        circle3.draw(mMVPMatrix);
        circle4.draw(mMVPMatrix);
        //circleImage.draw(mMVPMatrix);
        mBar.draw(mMVPMatrix);
    }
}