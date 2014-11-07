package com.example.owner.gameproject;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by home on 06/11/2014.
 */
public class Triangle {

    private FloatBuffer vertexBuffer;

    private int program;
    private int uColorLocation;
    private int aPositionLocation;

    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
             0.0f, 0.622008459f, 0.0f,
            -0.5f, 0.311004243f, 0.0f,
             0.5f, 0.311004243f, 0.0f,
    };

    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Triangle(int program) {
        this.program = program;

        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        uColorLocation = GLES20.glGetUniformLocation(program,"u_Color");
        aPositionLocation = GLES20.glGetAttribLocation(program,"a_Position");
    }

    public void draw(){
    }
}
