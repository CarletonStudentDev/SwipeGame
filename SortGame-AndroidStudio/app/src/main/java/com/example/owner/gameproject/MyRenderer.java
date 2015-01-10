package com.example.owner.gameproject;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements Renderer {
    private GLSurfaceView view;

    //Game Objects
    public MultiplierBar mBar;
    public TopBar topBar;
    public Card card;
    public GameBoard gameBoard;
    public Score score;
    public GameOverScreen gameOverScreen;
    public Timer timer;

    private float ratio;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Resources resources;

    public float mDeltaX;
    public float mDeltaY;

    public MyRenderer(Resources resources, GLSurfaceView view){
        this.view = view;
        this.resources = resources;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );

        ratio = (float) view.getWidth() / (float) view.getHeight();

        card = new Card(resources, 0.0f, -0.2f, R.color.blue);
        gameBoard = new GameBoard(resources);

        topBar = new TopBar(resources,-0.3f,0.9f);

        mBar = new MultiplierBar(resources, 0.0f, 0.7f);

        score = new Score(resources,0,0.9f);

        gameOverScreen = new GameOverScreen(resources, 0, -0.05f);
        timer = new Timer(resources,-0.075f,0.45f);
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

        //TODO see if we can make it just use 'card.mModelMatrix'
        Matrix.translateM(card.square.mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, card.square.mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;

        topBar.draw(mMVPMatrix);
        mBar.draw(mMVPMatrix);
        gameBoard.draw(mMVPMatrix);
        card.draw(scratch);
        score.draw(mMVPMatrix);
        timer.draw(mMVPMatrix);

        //gameOverScreen.draw(mMVPMatrix);

    }
}