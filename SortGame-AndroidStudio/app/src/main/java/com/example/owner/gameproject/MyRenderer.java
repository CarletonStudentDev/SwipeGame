package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements Renderer {
    private GLSurfaceView view;

    private MultiplierBar mBar;
    private TopBar topBar;
    public Card card;
    public GameBoard gameBoard;

    private Square quad1;
    private Square quad2;
    private Square quad3;
    private Square quad4;

    private float ratio;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Context context;

    public float mDeltaX;
    public float mDeltaY;

    private float mPreviousX;
    private float mPreviousY;

    public int blue = 0;
    public int green = 0;
    public int purple = 0;
    public int red = 0;

    public MyRenderer(Context context, GLSurfaceView view){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );

        this.ratio = view.getWidth() / view.getHeight();

        card = new Card(context, 0.0f, -0.2f, R.color.blue);
        gameBoard = new GameBoard(context);
        //X-POS,Y-POS,NumFilledHearts
        topBar = new TopBar(context,-0.3f,0.9f,2);
        //X-POS,Y-POS,NumFilledBlocks,MultiplierNum
        mBar = new MultiplierBar(context, 0.0f, 0.7f,2,2);

        float[] invis = {0f,0f,0f,0f};

        quad1 = new Square(context, 0.325f, 0.2f, 0.65f, 0.8f, GraphicsHelper.RGBArray(context, R.color.blue));
        quad2 = new Square(context, -0.325f, 0.2f, 0.65f, 0.8f, GraphicsHelper.RGBArray(context, R.color.green));
        quad3 = new Square(context, 0.325f, -0.6f, 0.65f, 0.8f, GraphicsHelper.RGBArray(context, R.color.purple));
        quad4 = new Square(context, -0.325f, -0.6f, 0.65f, 0.8f, GraphicsHelper.RGBArray(context, R.color.red));

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

        //TODO see if we can make it just use card.mModelMatrix
        Matrix.translateM(card.square.mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, card.square.mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;

        topBar.draw(mMVPMatrix);
        mBar.draw(mMVPMatrix);
        gameBoard.draw(mMVPMatrix);
        card.draw(scratch);

        quad1.draw(mMVPMatrix);
        quad2.draw(mMVPMatrix);
        quad3.draw(mMVPMatrix);
        quad4.draw(mMVPMatrix);

    }
}