package OpenGL;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import com.example.owner.gameproject.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import DrawableObjects.Card;
import DrawableObjects.GameBoard;
import DrawableObjects.MultiplierBar;
import DrawableObjects.Score;
import DrawableObjects.TopBar;
import Model.CardGenerator;
import Model.Game;
import Model.Multiplier;
import Model.Player;
import Model.Timer;

public class MyRenderer implements Renderer {
    private GLSurfaceView view;

    //Game Objects
    public MultiplierBar mBar;
    public TopBar topBar;
    public Card card;
    public GameBoard gameBoard;
    public Score score;
    private Game game;
    private CardGenerator cardGenerator;
    private Player player;
    private Multiplier multiplier;
    private Timer timer;


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
<<<<<<< HEAD
    public MyRenderer(Context context, GLSurfaceView view){
=======


    public MyRenderer(Resources resources, GLSurfaceView view)
    {
>>>>>>> Jeton2
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
        this.view = view;
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );

        this.player = new Player();
        this.multiplier = new Multiplier();
        this.game = new Game(this.player, this.multiplier);
        this.timer = new Timer(30000);
        this.cardGenerator = new CardGenerator();


        ratio = (float) view.getWidth() / (float) view.getHeight();
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d

        card = new Card(context, 0.0f, -0.2f, R.color.blue);
        gameBoard = new GameBoard(context);

        topBar = new TopBar(context,-0.3f,0.9f);
        Log.i("integer.toString: ", ""+this.player.getLives());
        topBar.setFullHearts(this.player.getLives());

        mBar = new MultiplierBar(context, 0.0f, 0.7f);

        score = new Score(context,0,0.9f);
<<<<<<< HEAD
=======
=======
>>>>>>> Jeton2
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }



    public boolean onTouchEvent(MotionEvent event)
    {
        if (event != null)
        {
            float r = (float)view.getHeight() / view.getWidth();

            // convert touch coordinates into OpenGL coordinates
            float newX = (-(event.getX() * 2) / view.getWidth() + 1f) / r;
            float newY = -(event.getY() * 2) / view.getHeight() + 1f;

            if(event.getAction() == MotionEvent.ACTION_DOWN){

                if (gameBoard.getQuadrant(newX, newY) == card.getColorId())
                    this.correct();

                else
                    this.incorrect();

            }

            /*if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                float deltaX = (x - mPreviousX) / r / 2f;
                float deltaY = (y - mPreviousY) / r / 2f;

                if(renderer.card.inShape(newX, newY)){
                    renderer.mDeltaX += deltaX;
                    renderer.mDeltaY += deltaY;
                }

            }*/
            //mPreviousX = x;
            //mPreviousY = y;

            return true;
        }
        return true;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
    private void correct()
    {
        this.game.correctMatch();
        this.mBar.increaseNumFull();

        score.addToScore(100, mBar.giveMulti());

        Log.i("score", Integer.toString(score.currentScore));

        score.addToScore(1,mBar.giveMulti());

        // generate a card
        card = cardGenerator.generateCard(this.context);
<<<<<<< HEAD
=======
=======
>>>>>>> Jeton2
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d

    }

    private void incorrect()
    {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
        this.game.incorrectMatch();
        mBar.reset();
        topBar.decreaseHearts();

        // generate a card
        card = cardGenerator.generateCard(this.context);
<<<<<<< HEAD
=======
=======
        if (this.gameTouchLogic.getTimer().timeOut())
            this.gameTouchLogic.timeOut();
>>>>>>> Jeton2
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
    }

    private void update()
    {
        if (this.timer.timeOut())
        {
            this.game.timeOut();
            mBar.reset();
            this.timer.resetTimer();
            topBar.decreaseHearts();
        }



        // notify the listeners that the Player's lives have finished
        //this.game.livesFinish();



    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        update();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        float[] scratch = new float[16];

        //TODO see if we can make it just use 'card.mModelMatrix'
        Matrix.translateM(card.getSquare().mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, card.getSquare().mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
        topBar.draw(mMVPMatrix);
        mBar.draw(mMVPMatrix);
        gameBoard.draw(mMVPMatrix);
        card.draw(scratch);
        score.draw(mMVPMatrix);
<<<<<<< HEAD
=======
=======
        DrawObjects.draw(this.gameTouchLogic, mMVPMatrix, scratch);

>>>>>>> Jeton2
>>>>>>> 25594c0a10fc0fb1a41cb825e6af987b42037f0d
    }
}