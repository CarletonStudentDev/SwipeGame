package OpenGL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Vector Image which gets drawn to the renderer
 * holds the image to be rendered
 *
 * @Robert Fernandes
 *
 * @version 1.0
 * @since 2014-12-01
 *
 */

public class Drawable {


    /*
     * Matrix which represents the current shape's position
     */
    public float[] mModelMatrix = new float[16];

    /*
     * shader codes
     */
    private String vertexShaderCode;
    private String fragmentShaderCode;

    /*
     * memory locations for OpenGL
     */
    private int uColorLocation;
    private int aPositionLocation;
    private int mMVPMatrixHandle;
    private int textureUniformHandle;
    private int texturePositionHandle;
    private int textureHandle;

    /*
     * # of coords per vertex
     */
    private int coordsPerVertex;

    /*
     * pointer to the shader program
     */
    private int program;

    /*
     * Buffers
     * (Theyre causing the memory leaks and will be fixed soon)
     */
    private FloatBuffer vertexData;
    private FloatBuffer textureData;
    private ShortBuffer drawListBuffer;

    /*
     * vector image specific coords, draw order, color, and texture
     */
    private float[] coords;
    private short[] drawOrder;
    private float[] color;
    private float[] textureCoords;

    /*
     * draw with lines, dots or triangles
     */
    private int drawType;

    private boolean useTexture = false;// use a texture or not

    //x and y coordinates of the shape (center of shape)
    //based off of the model matrix
    public float x;
    public float y;

    // trying to get sprite sheets working
    private float[] frameCoords = {
            0f, 0f,
            0f, 1f,
            1f, 1f,
            1f, 0f
    };

    /**
     * Constructor for a drawable object
     *
     * @param coordsPerVertex: number of coordinates that apply to one vertex
     *                         (2 coordinates per vertex for 2D
     *                          can also store color data for each vertex)
     * @param drawType: draw the shape using triangles, lines or points
     */
    public Drawable(int coordsPerVertex, int drawType) {
        this.drawType = drawType;
        this.coordsPerVertex = coordsPerVertex;
        Matrix.setIdentityM(mModelMatrix, 0);
    }

    /**
     * creating byte buffers for vertexData(shape and position) textureData(texture shape)
     * and drawOrder(order we want to draw our shape)
     */
    public void initializeBuffers(){
        vertexData = ByteBuffer
                .allocateDirect(coords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData
                .put(coords)
                .position(0);

        if(useTexture) {//  true if setTexture is called
            textureData = ByteBuffer
                    .allocateDirect(textureCoords.length * 4)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer();
            textureData
                    .put(textureCoords)
                    .position(0);
        }

        drawListBuffer = ByteBuffer
                .allocateDirect(drawOrder.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        drawListBuffer
                .put(drawOrder)
                .position(0);
    }

    public void initializeShaderProgram(){
        program = GraphicsHelper.createProgram(vertexShaderCode, fragmentShaderCode);
        GLES20.glUseProgram(program);
    }

    public void draw(float[] mvpMatrix){
        initializeShaderProgram();

        //set the x and y position of the shape
        //really cheese method
        //not being used right now
        x = mModelMatrix[12];
        y = mModelMatrix[13];

        // pass Information pointers to OpenGL shaders
        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");

        if(useTexture) {    //useTexture set when setTexture is called
                            //texture specific pointers passed to OpenGL shaders
            texturePositionHandle = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
            textureUniformHandle = GLES20.glGetUniformLocation(program, "u_Texture");
        }

        //set the pointer to the shape coordinates
        GLES20.glVertexAttribPointer(aPositionLocation, coordsPerVertex, GLES20.GL_FLOAT, false, coordsPerVertex * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        if(useTexture) {    //useTexture set when setTexture is called
                            //texture specific pointer to the texture coordinates
            GLES20.glVertexAttribPointer(texturePositionHandle, coordsPerVertex, GLES20.GL_FLOAT, true, coordsPerVertex * 4, textureData);
            GLES20.glEnableVertexAttribArray(texturePositionHandle);
        }

        //set the color to be rendered
        GLES20.glUniform4fv(uColorLocation, 1, color, 0);

        if(useTexture) {    //useTexture set when setTexture is called
                            //enabling texture to be rendered over the color
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
            GLES20.glUniform1i(textureUniformHandle, 0);
        }

        // projection for different sized screens
        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        //draw to the screen
        GLES20.glDrawElements(drawType, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }

    /**
     * set the shader code specific to shape being drawn
     * (different shaders for textures vs non textures)
     *
     * @param vertexShaderCode: vertex shader code
     * @param fragmentShaderCode: fragment shader code
     */
    public void setShaderCode(String vertexShaderCode, String fragmentShaderCode){
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
    }

    /**
     * set the texture, and also set useTexture to true
     * to enable texture specific OpenGL calls
     *
     * @param context: holds references to resources to be used in loadTexture
     * @param bitmapId: specific id of the texture
     */
    public void setTexture(Context context, int bitmapId){
        useTexture = true;
        textureHandle = loadTexture(context, bitmapId);
    }

    /**
     * sets the coordinates of the shape
     * (sets the shape as well)
     *
     * @param coords: coordinates of the shape
     */
    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    /**
     * sets the coordinates of the texture
     * (only called while useTexture == true)
     * texture coordinates differ for different shapes
     *
     * @param coords
     */
    public void setTextureCoords(float[] coords){
        this.textureCoords = coords;
    }

    /**
     * sets the draw order, OpenGL only draws in lines points and triangles
     * to draw a square, you need to tell OpenGL to draw 2 triangles to make a square
     *
     * @param drawOrder: draw order in which to draw your shape using the coordinates
     */
    public void setDrawOrder(short[] drawOrder){
        this.drawOrder = drawOrder;
    }

    /**
     * sets the color of the shape
     *
     * @param color: color of the shape represented in an RGBA array
     */
    public void setColor(float[] color){
        this.color = color;
    }

    /**
     * loads the texture into memory and returns a pointer to it's location in memory
     *
     * @param context: reference to the resources
     * @param bitmapId: texture specific id
     * @return: returns textureHandle which holds the location of the texture in memory
     */
    public static int loadTexture(Context context, int bitmapId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);

        final int[] textureHandle = new int[1];
        GLES20.glDeleteTextures(1,textureHandle,0);
        GLES20.glGenTextures(1, textureHandle, 0);
        //check if the textureHandle is already pointing to something
        if(textureHandle[0] != 0){
            // set textureHandle to point to bitmap
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            //telling OpenGL how to render the texture
            //GL_NEAREST -> nearest pixel (no blur)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            //tells OpenGL how to render the texture on the shape
            //GL_CLAMP_TO_EDGE -> clamp edge of texture to the edge of the shape
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();
        }
        if(textureHandle[0] == 0){  //error handling
            throw new RuntimeException("Error loading texture.");
        }
        return textureHandle[0];
    }

    public int getTextureHandle()
    {
        return this.textureHandle;
    }

}