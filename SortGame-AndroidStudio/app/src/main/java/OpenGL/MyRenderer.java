package OpenGL;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

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

        this.player = new Player();
        this.multiplier = new Multiplier();


        this.game = new Game(this.player, this.multiplier);

        ratio = (float) view.getWidth() / (float) view.getHeight();

        card = new Card(resources, 0.0f, -0.2f, R.color.blue);
        gameBoard = new GameBoard(resources);

        topBar = new TopBar(resources,-0.3f,0.9f);

        mBar = new MultiplierBar(resources, 0.0f, 0.7f);

        score = new Score(resources,0,0.9f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public void update()
    {
        // game logic

        // boolean value to indicate that a correct match has been found
        boolean correctMatchFound;

        // Timer instance representing the countdown timer
        Timer timer;

        Deck d = (Deck)this.getDeck();

        // main loop check if number of lives != 0
        if (this.player.getLives() != 0)
        {
            // create new deck
            this.game.roundsOver();

            // round loop check if number of cards in deck != 0
            while(d.deckSize() != 0)
            {
                // draw card
                this.drawCard();

                // set boolean values to false
                this.game.setTimedOut(false);
                correctMatchFound = false;

                // set Timer
                timer = new Timer(30, this.game);
                timer.start();

                // drawn card loop to check if not time out and no correct match found
                while (!this.game.getTimedOut() && !correctMatchFound)
                {
                    // check match if correct notify listeners of correct match
                    if (this.game.checkMatch())
                    {
                        correctMatchFound = true;
                        this.game.correctMatch();
                    }

                    // otherwise notify listeners of incorrect match
                    else
                        this.game.incorrectMatch();

                }

                // cancel the time
                timer.cancel();
            }

        }

        // notify the listeners that the Player's lives have finished
        this.game.livesFinish();



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

    }
}