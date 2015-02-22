package carletonstudentdev.googleplayservices;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements Renderer
{

	// Our matrices
	private final float[] mtrxProjection = new float[16],
	                      mtrxView = new float[16],
	                      mtrxProjectionAndView = new float[16];
	
	// Geometric variables
	public static float vertices[],
                             uvs[];

    private static short indices[];


    // buffers
    private FloatBuffer vertexBuffer,
                        uvBuffer;

	private ShortBuffer drawListBuffer;


    // textManager instance
    private TextManager tm;


	// Our screenresolution
    private float mScreenWidth,
                  mScreenHeight,
	              ssu,
	              ssx,
	              ssy,
	              swp,
	              shp;

	// Misc
	private Context mContext;

	public GLRenderer(Context context)
	{
		this.mContext = context;
        this.ssu = 1.0f;
        this.ssx = 1.0f;
        this.ssy = 1.0f;
        this.swp = 320.0f;
        this.shp = 480.0f;

	}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        this.mScreenHeight = this.mContext.getResources().getDisplayMetrics().heightPixels;
        this.mScreenWidth = this.mContext.getResources().getDisplayMetrics().widthPixels;

        // Setup our scaling system
        //this.setupScaling();

        // Create the triangles
        this.setupTriangle();

        // Create the image information
        this.setupImage();

        // Create our texts
        this.setupText();

        // Set the clear color to black
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1);

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
	public void onSurfaceChanged(GL10 gl, int width, int height)
    {
		// We need to know the current width and height.
		this.mScreenWidth = width;
		this.mScreenHeight = height;
		
		// Redo the Viewport, making it fullscreen.
		GLES20.glViewport(0, 0, (int)mScreenWidth, (int)mScreenHeight);
		
		// Clear our matrices
	    for(int i = 0; i < 16; i++)
	    {
	    	this.mtrxProjection[i] = 0.0f;
	    	this.mtrxView[i] = 0.0f;
	    	this.mtrxProjectionAndView[i] = 0.0f;
	    }
	    
	    // Setup our screen width and height for normal sprite translation.
	    Matrix.orthoM(this.mtrxProjection, 0, 0f, this.mScreenWidth, 0f, this.mScreenHeight, 0, 50);
	    
	    // Set the camera position (View matrix)
        Matrix.setLookAtM(this.mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(this.mtrxProjectionAndView, 0, this.mtrxProjection, 0, this.mtrxView, 0);
        
        // Setup our scaling system
		//this.setupScaling();
	}




    @Override
    public void onDrawFrame(GL10 unused)
    {
        // Render our example
        this.render(this.mtrxProjectionAndView);

        // Render the text
        if(this.tm != null)
            this.tm.draw(this.mtrxProjectionAndView);

    }

    private void render(float[] m)
    {
        // Set our shaderprogram to image shader
        GLES20.glUseProgram(riGraphicTools.sp_Image);

        // clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // get handle to vertex shader's vPosition member and add vertices
        int mPositionHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_Image, "vPosition");
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Get handle to texture coordinates location and load the texture uvs
        int mTexCoordLoc = GLES20.glGetAttribLocation(riGraphicTools.sp_Image, "a_texCoord" );
        GLES20.glVertexAttribPointer ( mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, this.uvBuffer);
        GLES20.glEnableVertexAttribArray ( mTexCoordLoc );

        // Get handle to shape's transformation matrix and add our matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_Image, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        // Get handle to textures locations
        int mSamplerLoc = GLES20.glGetUniformLocation (riGraphicTools.sp_Image, "s_texture" );

        // Set the sampler texture unit to 0, where we have saved the texture.
        GLES20.glUniform1i ( mSamplerLoc, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, this.drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordLoc);

    }
	
	private void setupText()
	{
		// Create our text manager
		this.tm = new TextManager();
		
		// Tell our text manager to use index 1 of textures loaded
		this.tm.setTextureID(1);

		// Create our new textobject
		//TextObject txt = new TextObject("hello world", 10f, 10f);
        TextObject txt = new TextObject();

		// Add it to our manager
		this.tm.addText(txt);

        txt = new TextObject("HI Jeton", 80, this.mScreenWidth, 5f);

        // set size of text
        this.tm.setUniformscale(txt.getTextSize());

        this.tm.addText(txt);

	}


	private void setupScaling()
	{
		// The screen resolutions
		this.swp = this.mContext.getResources().getDisplayMetrics().widthPixels;
		this.shp = this.mContext.getResources().getDisplayMetrics().heightPixels;
		
		// Orientation is assumed portrait
		this.ssx = this.swp / 320.0f;         // divided by swp original
		this.ssy = this.shp / 480.0f;         // divided by shp original
		
		// Get our uniform scaler
		if(this.ssx > this.ssy)
    		this.ssu = this.ssy;

        else
    		this.ssu = this.ssx;
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
		int id = this.mContext.getResources().getIdentifier("drawable/font", null, this.mContext.getPackageName());
		Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
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
