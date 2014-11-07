package com.example.owner.gameproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;

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

    private static final int POSITION_COMPONENT_COUNT = 2;

    private int program;

    private FloatBuffer vertexData;
    private ShortBuffer drawListBuffer;

    private float x = 0f;
    private float y = 0f;
    private float size = 1f;
    private float xSpeed = 0.01f;

    private float[] squareCoords;

    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
    private float[] color = { 192f/255f, 39f/255f, 60/255f, 1.0f};
    private GLSurfaceView view;
    private int mMVPMatrixHandle;

    public Square(GLSurfaceView view, float size, float x, float y) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.view = view;
    }

    public void update(){
        if (x + size >= 1){
            xSpeed = -xSpeed;
        } else if (x - size <= -1){
            xSpeed = -xSpeed;
        }
        x += xSpeed;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public void draw(){
        update();
        squareCoords = new float[] {
                 size + x,  size + y,
                 size + x, -size + y,
                -size + x, -size + y,
                -size + x,  size + y,
        };

        vertexData = ByteBuffer
                .allocateDirect(squareCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(squareCoords);

        drawListBuffer = ByteBuffer
                .allocateDirect(drawOrder.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        drawListBuffer.put(drawOrder);

        String fragmentShaderCode =
                "precision mediump float;" +
                        "uniform vec4 vColor;" +
                        "void main() {" +
                        "   gl_FragColor = vColor;" +
                        "}";
        String vertexShaderCode =
                "attribute vec4 vPosition;" +
                        "void main() {" +
                        "   gl_Position = vPosition;" +
                        "}";

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderCode);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderCode);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        GLES20.glUseProgram(program);

        uColorLocation = GLES20.glGetUniformLocation(program, "vColor");
        aPositionLocation = GLES20.glGetAttribLocation(program, "vPosition");

        vertexData.position(0);
        drawListBuffer.position(0);

        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, POSITION_COMPONENT_COUNT * 4, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glUniform4fv(uColorLocation, 1, color, 0);
        // GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 4);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }
}
