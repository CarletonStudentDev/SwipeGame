package OpenGL;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import DrawableObjects.DrawObjects;
import Model.GameSetup;
import Model.GameTouchLogic;

public class MyRenderer implements Renderer {
    private GLSurfaceView view;

    private GameSetup gameSetup;
    private GameTouchLogic gameTouchLogic;

    private float ratio;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Context context;

    public float mDeltaX;
    public float mDeltaY;

<<<<<<< HEAD
    public MyRenderer(Context context, GLSurfaceView view){
=======


    public MyRenderer(Resources resources, GLSurfaceView view)
    {
>>>>>>> Jeton2
        this.view = view;
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
    {
        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );

        this.gameSetup = new GameSetup(this.resources, 10000);
        this.gameTouchLogic = new GameTouchLogic(this.view, this.gameSetup);

        ratio = (float) view.getWidth() / (float) view.getHeight();
<<<<<<< HEAD

        card = new Card(context, 0.0f, -0.2f, R.color.blue);
        gameBoard = new GameBoard(context);

        topBar = new TopBar(context,-0.3f,0.9f);
        Log.i("integer.toString: ", ""+this.player.getLives());
        topBar.setFullHearts(this.player.getLives());

        mBar = new MultiplierBar(context, 0.0f, 0.7f);

        score = new Score(context,0,0.9f);
=======
>>>>>>> Jeton2
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);
        this.ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }


    public boolean onTouchEvent(MotionEvent event)
    {
        return this.gameTouchLogic.onTouchEvent(event);
    }

<<<<<<< HEAD
    private void correct()
    {
        this.game.correctMatch();
        this.mBar.increaseNumFull();

        score.addToScore(100, mBar.giveMulti());

        Log.i("score", Integer.toString(score.currentScore));

        score.addToScore(1,mBar.giveMulti());

        // generate a card
        card = cardGenerator.generateCard(this.context);
=======
>>>>>>> Jeton2

    private void checkTime()
    {
<<<<<<< HEAD
        this.game.incorrectMatch();
        mBar.reset();
        topBar.decreaseHearts();

        // generate a card
        card = cardGenerator.generateCard(this.context);
=======
        if (this.gameTouchLogic.getTimer().timeOut())
            this.gameTouchLogic.timeOut();
>>>>>>> Jeton2
    }



    @Override
    public void onDrawFrame(GL10 gl10)
    {
        this.checkTime();

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        float[] scratch = new float[16];

        //TODO see if we can make it just use 'card.mModelMatrix'
        Matrix.translateM(this.gameSetup.getCard().getSquare().mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, this.gameSetup.getCard().getSquare().mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;

<<<<<<< HEAD
        topBar.draw(mMVPMatrix);
        mBar.draw(mMVPMatrix);
        gameBoard.draw(mMVPMatrix);
        card.draw(scratch);
        score.draw(mMVPMatrix);
=======
        DrawObjects.draw(this.gameTouchLogic, mMVPMatrix, scratch);

>>>>>>> Jeton2
    }



}