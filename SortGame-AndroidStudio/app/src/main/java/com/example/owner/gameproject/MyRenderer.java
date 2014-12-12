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

    private CircleImage circleImage;
    private CircleImage circleImage2;
    private CircleImage circleImage3;
    private CircleImage circleImage4;

    private float ratio;

    private Square square;
    private Square square2;

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

    public boolean onTouchEvent(MotionEvent event){

        float r = (float)view.getHeight() / view.getWidth();

        float x = event.getX();
        float y = event.getY();

        // convert touch coordinates into OpenGL coordinates
        float newX = (-(event.getX() * 2) / view.getWidth() + 1f) / r;
        float newY = -(event.getY() * 2) / view.getHeight() + 1f;

        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {


            float deltaX = (x - mPreviousX) / r / 2f;
            float deltaY = (y - mPreviousY) / r / 2f;

            if(card.inShape(newX, newY)){
                mDeltaX += deltaX;
                mDeltaY += deltaY;
            }

        }
        mPreviousX = x;
        mPreviousY = y;

        return true;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 171f/255f, 34f/255f, 52f/255f, 1.0f );

        int color = context.getResources().getColor(R.color.darkestRed);
        square = new Square(context, 1.4f, 0.2f, 0f, 0.9f, color);

        int color2 = context.getResources().getColor(R.color.darkRed);
        square2 = new Square(context, 1.1f, 0.2f, 0f, 0.7f, color2);

        card = new Card(context, R.drawable.caution, 0.0f, -0.2f);
        //circle = new Circle(context, 0.5f, 0, 0, color);
        circleImage = new CircleImage(context, 0.35f, 0.4f, 0.35f, R.drawable.red);
        circleImage2 = new CircleImage(context, 0.35f, -0.4f, 0.35f, R.drawable.purple);
        circleImage3 = new CircleImage(context, 0.35f, -0.4f, -0.75f, R.drawable.blue);
        circleImage4 = new CircleImage(context, 0.35f, 0.4f, -0.75f, R.drawable.green);

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
        square.draw(scratch);
        square2.draw(scratch);
        circleImage.draw(scratch);
        circleImage2.draw(scratch);
        circleImage3.draw(scratch);
        circleImage4.draw(scratch);
        //circleImage.draw(mMVPMatrix);
    }
}