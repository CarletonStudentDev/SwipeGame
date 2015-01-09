package com.example.owner.gameproject;

import android.graphics.Bitmap;
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
 * @author name (i.e. John Smith)
 *
 * @version version_number
 * @since YYYY-MM-DD
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

    private boolean useTexture = false;

    public float x;
    public float y;

    public Drawable(int coordsPerVertex, int drawType) {
        this.drawType = drawType;
        this.coordsPerVertex = coordsPerVertex;
        Matrix.setIdentityM(mModelMatrix, 0);
    }

    public void initializeBuffers(){
        vertexData = ByteBuffer
                .allocateDirect(coords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData
                .put(coords)
                .position(0);

        if(useTexture) {
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

        x = mModelMatrix[12];
        y = mModelMatrix[13];

        // pass Information pointers
        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");

        if(useTexture) {
            texturePositionHandle = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
            textureUniformHandle = GLES20.glGetUniformLocation(program, "u_Texture");
        }

        GLES20.glVertexAttribPointer(aPositionLocation, coordsPerVertex, GLES20.GL_FLOAT, false, coordsPerVertex * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        if(useTexture) {
            GLES20.glVertexAttribPointer(texturePositionHandle, coordsPerVertex, GLES20.GL_FLOAT, true, coordsPerVertex * 4, textureData);
            GLES20.glEnableVertexAttribArray(texturePositionHandle);
        }

        GLES20.glUniform4fv(uColorLocation, 1, color, 0);

        if(useTexture) {
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
            GLES20.glUniform1i(textureUniformHandle, 0);
        }

        // projection for different sized screens
        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(drawType, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }

    public void setShaderCode(String vertexShaderCode, String fragmentShaderCode){
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
    }

    public void setTexture(Bitmap bitmap){
        useTexture = true;
        textureHandle = loadTexture(bitmap);
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    public void setTextureCoords(float[] coords){
        this.textureCoords = coords;
    }

    public void setDrawOrder(short[] drawOrder){
        this.drawOrder = drawOrder;
    }

    public void setColor(float[] color){
        this.color = color;
    }

    public static int loadTexture(Bitmap bitmap){
        final int[] textureHandle = new int[1];
        GLES20.glDeleteTextures(1,textureHandle,0);
        GLES20.glGenTextures(1, textureHandle, 0);
        if(textureHandle[0] != 0){
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        }
        if(textureHandle[0] == 0){
            throw new RuntimeException("Error loading texture.");
        }
        return textureHandle[0];
    }
}
