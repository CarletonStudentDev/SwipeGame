package com.example.owner.gameproject;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by home on 06/11/2014.
 */
public class Square {

    private int uColorLocation;
    private int aPositionLocation;

    private static final int POSITION_COMPONENT_COUNT = 2;

    private int program;

    private FloatBuffer vertexData;

    private float x = 0f;
    private float y = 0f;

    private float[] squareCoords;

    public Square(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public void update(){
        x += 0.01;
    }

    public void draw(){
        update();

        squareCoords = new float[]{
                -0.5f + x, 0.5f + y,
                0.5f + x, 0.5f + y,
                -0.5f + x, -0.5f + y,

                0.5f + x, 0.5f + y,
                0.5f + x, -0.5f + y,
                -0.5f + x, -0.5f + y,
        };
        vertexData = ByteBuffer
                .allocateDirect(squareCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(squareCoords);

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

        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glUniform4f(uColorLocation, 1.0f, 0f, 0f, 1f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        GLES20.glDisableVertexAttribArray(aPositionLocation);
    }
}
