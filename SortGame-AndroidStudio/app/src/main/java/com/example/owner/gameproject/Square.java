package com.example.owner.gameproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by home on 06/11/2014.
 */
public class Square {

    private int uColorLocation;
    private int aPositionLocation;
    private int mMVPMatrixHandle;
    private int textureUniformHandle;
    private int texturePositionHandle;
    private int textureHandle;

    private FloatBuffer vertexData;
    private FloatBuffer textureData;
    private ShortBuffer drawListBuffer;

    private int program;

    private static final int POSITION_COMPONENT_COUNT = 2;
    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
    private float[] color = { 192f/255f, 39f/255f, 60/255f, 1.0f};

    private float[] squareCoords;
    private float x = 0f;
    private float y = 0f;
    private float size = 1f;
    private float xSpeed = 0.01f;
    private GLSurfaceView view;

    public Square(Context context, GLSurfaceView view, float size, float x, float y) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.view = view;

        program = ShaderHelper.createProgram();
        GLES20.glUseProgram(program);
        textureHandle = ShaderHelper.loadTexture(context, R.drawable.ic_launcher);
    }

    public void update(){

        float ratio = (float) view.getWidth() / view.getHeight();

        if (x + size >= ratio){
            xSpeed = -xSpeed;
        } else if (x - size <= -ratio){
            xSpeed = -xSpeed;
        }
        x += xSpeed;
    }

    public void draw(float[] mvpMatrix){
        update();
        squareCoords = new float[] {
                 size + x,  size + y,
                 size + x, -size + y,
                -size + x, -size + y,
                -size + x,  size + y,
        };
        float[] textureCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,
        };

        vertexData = ByteBuffer
                .allocateDirect(squareCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData
                .put(squareCoords)
                .position(0);

        textureData = ByteBuffer
                .allocateDirect(textureCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        textureData
                .put(textureCoords)
                .position(0);

        drawListBuffer = ByteBuffer
                .allocateDirect(drawOrder.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        drawListBuffer
                .put(drawOrder)
                .position(0);

        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");
        texturePositionHandle = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
        textureUniformHandle = GLES20.glGetUniformLocation(program, "u_Texture");

        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, POSITION_COMPONENT_COUNT * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glVertexAttribPointer(texturePositionHandle, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, true, POSITION_COMPONENT_COUNT * 4, textureData);
        GLES20.glEnableVertexAttribArray(texturePositionHandle);
        //GLES20.glUniform4fv(uColorLocation, 1, color, 0);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);
        GLES20.glUniform1i(textureUniformHandle, 0);

        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }
}