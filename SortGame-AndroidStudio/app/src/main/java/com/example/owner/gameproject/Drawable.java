package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2014-11-14
 * Created by Robert on 14/11/2014.
 */
public class Drawable {

    private String vertexShaderCode;
    private String fragmentShaderCode;

    private int uColorLocation;
    private int aPositionLocation;
    private int mMVPMatrixHandle;
    private int textureUniformHandle;
    private int texturePositionHandle;
    private int textureHandle;

    private int coordsPerVertex;

    private int program;

    private FloatBuffer vertexData;
    private FloatBuffer textureData;
    private ShortBuffer drawListBuffer;

    private float[] coords;
    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
    private float[] color = { 1f/255f, 39f/255f, 60/255f, 1.0f};
    private Context context;

    public Drawable(Context context, int coordsPerVertex) {
        this.coordsPerVertex = coordsPerVertex;
        this.context = context;
    }

    public void initializeBuffers(){
        vertexData = ByteBuffer
                .allocateDirect(coords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData
                .put(coords)
                .position(0);

  /*
        textureData = ByteBuffer
                .allocateDirect(textureCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        textureData
                .put(textureCoords)
                .position(0);
*/
        drawListBuffer = ByteBuffer
                .allocateDirect(drawOrder.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        drawListBuffer
                .put(drawOrder)
                .position(0);
    }

    public void initializeShaderProgram(){
        program = ShaderHelper.createProgram(vertexShaderCode, fragmentShaderCode);
        GLES20.glUseProgram(program);
    }

    public void draw(float[] mvpMatrix){

        initializeBuffers();
        initializeShaderProgram();
        // pass Information pointers
        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");
        //textureHandle = ShaderHelper.loadTexture(context, R.drawable.ic_launcher);
        //texturePositionHandle = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
        //textureUniformHandle = GLES20.glGetUniformLocation(program, "u_Texture");

        GLES20.glVertexAttribPointer(aPositionLocation, coordsPerVertex, GLES20.GL_FLOAT, false, coordsPerVertex * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        //GLES20.glVertexAttribPointer(texturePositionHandle, coordsPerVertex, GLES20.GL_FLOAT, true, coordsPerVertex * 4, textureData);
        //GLES20.glEnableVertexAttribArray(texturePositionHandle);

        GLES20.glUniform4fv(uColorLocation, 1, color, 0);
/*
        // this gets called with a texture type shader
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
        GLES20.glUniform1i(textureUniformHandle, 0);
*/
        // projection for different sized screens
        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }

    public void setShaderCode(String vertexShaderCode, String fragmentShaderCode){
        this.vertexShaderCode = vertexShaderCode;
        this.fragmentShaderCode = fragmentShaderCode;
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    public void setDrawOrder(short[] drawOrder){
        this.drawOrder = drawOrder;
    }

    public void setColor(float[] color){
        this.color = color;
    }
}
