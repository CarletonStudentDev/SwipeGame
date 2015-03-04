package OpenGL;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.view.MotionEvent;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import DrawableObjects.DrawObjects;
import Model.GameSetup;
import Model.GameTouchLogic;


public class MyRenderer implements Renderer {

    private final float[] mMVPMatrix = new float[16],
                          mProjectionMatrix = new float[16],
                          mViewMatrix = new float[16];

    private Activity activity;
    private GLSurfaceView view;

    private GameSetup gameSetup;
    private GameTouchLogic gameTouchLogic;


    // Geometric variables
    public static float vertices[],
            uvs[];

    private static short indices[];


    // buffers
    private FloatBuffer vertexBuffer,
                        uvBuffer;

    private ShortBuffer drawListBuffer;

    private float ratio,
                  mDeltaX,
                  mDeltaY;

    private long gameLength;

    // Our screenresolution
    private float mScreenWidth,
            mScreenHeight,
            ssu,
            ssx,
            ssy,
            swp,
            shp;




    public MyRenderer(GLSurfaceView view, Activity activity, long gameLength)
    {
        this.view = view;
        this.gameLength = gameLength;
        this.activity = activity;
        this.ssu = 1.0f;
        this.ssx = 1.0f;
        this.ssy = 1.0f;
        this.swp = 320.0f;
        this.shp = 480.0f;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
    {
        // Create the triangles
        this.setupTriangle();

        // Create the image information
        this.setupImage();

        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );
        this.gameSetup = new GameSetup(this.activity, gameLength);
        this.gameTouchLogic = new GameTouchLogic(this.view, this.gameSetup);
        ratio = (float) view.getWidth() / (float) view.getHeight();


        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        // Create the shaders, images
        int vertexShader = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_Image);
        int fragmentShader = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_Image);

        riGraphicTools.sp_Image = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(riGraphicTools.sp_Image, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(riGraphicTools.sp_Image, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_Image);                  // creates OpenGL ES program executables

        // Text shader
        int vshadert = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_Text);
        int fshadert = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_Text);

        riGraphicTools.sp_Text = GLES20.glCreateProgram();
        GLES20.glAttachShader(riGraphicTools.sp_Text, vshadert);
        GLES20.glAttachShader(riGraphicTools.sp_Text, fshadert); 		// add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_Text);                  // creates OpenGL ES program executables

        // Set our shader programm
        GLES20.glUseProgram(riGraphicTools.sp_Image);
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


    @Override
    public void onDrawFrame(GL10 gl10)
    {

        GLES20.glClearColor( 236f/255f, 240f/255f, 241f/255f, 1.0f );
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        float[] scratch = new float[16];

        //TODO see if we can make it just use 'card.mModelMatrix'
        //TODO try implementing game over animation
        Matrix.translateM(this.gameTouchLogic.getCard().getSquare().mModelMatrix, 0, -mDeltaX * (ratio / 100f), -mDeltaY * (ratio / 100f), 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, this.gameTouchLogic.getCard().getSquare().mModelMatrix, 0);
        mDeltaX = 0f;
        mDeltaY = 0f;


        DrawObjects.draw(this.gameTouchLogic, mMVPMatrix);
    }


    private void setupImage()
    {
        // We will use a randomizer for randomizing the textures from texture atlas.
        // This is strictly optional as it only effects the output of our app,
        // Not the actual knowledge.


        // Generate Textures, if more needed, alter these numbers.
        int[] texturenames = new int[1];
        GLES20.glGenTextures(1, texturenames, 0);


        // Again for the text texture
        int id = this.activity.getResources().getIdentifier("drawable/font", null, this.activity.getPackageName());
        Bitmap bmp = BitmapFactory.decodeResource(this.activity.getResources(), id);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 1);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        bmp.recycle();
    }

    private void setupTriangle()
    {
        // We will need a randomizer
        Random rnd = new Random();

        // Our collection of vertices
        vertices = new float[30*4*3];

        // Create the vertex data
        for(int i=0;i<30;i++)
        {
            int offset_x = rnd.nextInt((int)swp);
            int offset_y = rnd.nextInt((int)shp);

            // Create the 2D parts of our 3D vertices, others are default 0.0f
            vertices[(i*12)] = offset_x;
            vertices[(i*12) + 1] = offset_y + (30.0f*ssu);
            vertices[(i*12) + 2] = 0f;
            vertices[(i*12) + 3] = offset_x;
            vertices[(i*12) + 4] = offset_y;
            vertices[(i*12) + 5] = 0f;
            vertices[(i*12) + 6] = offset_x + (30.0f*ssu);
            vertices[(i*12) + 7] = offset_y;
            vertices[(i*12) + 8] = 0f;
            vertices[(i*12) + 9] = offset_x + (30.0f*ssu);
            vertices[(i*12) + 10] = offset_y + (30.0f*ssu);
            vertices[(i*12) + 11] = 0f;
        }

        // The indices for all textured quads
        indices = new short[30*6];
        int last = 0;

        for(int i=0;i<30;i++)
        {
            // We need to set the new indices for the new quad
            indices[(i*6)] = (short) last;
            indices[(i*6) + 1] = (short) (last + 1);
            indices[(i*6) + 2] = (short) (last + 2);
            indices[(i*6) + 3] = (short) last;
            indices[(i*6) + 4] = (short) (last + 2);
            indices[(i*6) + 5] = (short) (last + 3);

            // Our indices are connected to the vertices so we need to keep them
            // in the correct order.
            // normal quad = 0,1,2,0,2,3 so the next one will be 4,5,6,4,6,7
            last = last + 4;
        }


        // 30 imageobjects times 4 vertices times (u and v)
        uvs = new float[30*4*2];

        // We will make 30 randomly textures objects
        for(int i=0; i<30; i++)
        {
            int random_u_offset = rnd.nextInt(2);
            int random_v_offset = rnd.nextInt(2);

            // Adding the UV's using the offsets
            uvs[(i*8)] = random_u_offset * 0.5f;
            uvs[(i*8) + 1] = random_v_offset * 0.5f;
            uvs[(i*8) + 2] = random_u_offset * 0.5f;
            uvs[(i*8) + 3] = (random_v_offset+1) * 0.5f;
            uvs[(i*8) + 4] = (random_u_offset+1) * 0.5f;
            uvs[(i*8) + 5] = (random_v_offset+1) * 0.5f;
            uvs[(i*8) + 6] = (random_u_offset+1) * 0.5f;
            uvs[(i*8) + 7] = random_v_offset * 0.5f;
        }

        // The texture buffer
        ByteBuffer bb = ByteBuffer.allocateDirect(uvs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.uvBuffer = bb.asFloatBuffer();
        this.uvBuffer.put(uvs);
        this.uvBuffer.position(0);

        // The vertex buffer.
        ByteBuffer bb1 = ByteBuffer.allocateDirect(vertices.length * 4);
        bb1.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb1.asFloatBuffer();
        this.vertexBuffer.put(vertices);
        this.vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        this.drawListBuffer = dlb.asShortBuffer();
        this.drawListBuffer.put(indices);
        this.drawListBuffer.position(0);
    }
}