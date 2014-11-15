package com.example.owner.gameproject;

import android.content.Context;

/**
 * Created by home on 06/11/2014.
 */
public class Square extends Drawable{

    private static String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform sampler2D u_Texture;" +
                    "varying vec2 v_TexCoordinate;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "   gl_FragColor = vColor;" +
                    //"   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);" +
                    "}";
    private static String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 a_TexCoordinate;" +
                    "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "   v_TexCoordinate = a_TexCoordinate;" +
                    "   gl_Position = uMVPMatrix * vPosition;" +
                    "}";


    private static final int COORDINATES_PER_VERTEX = 2;
    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
    private float[] color = { 192f/255f, 39f/255f, 60/255f, 1.0f};

    private float[] squareCoords = {
            0.5f,  0.5f,
            0.5f, -0.5f,
            -0.5f, -0.5f,
            -0.5f,  0.5f,
    };

    public Square(Context context) {
        super(context, COORDINATES_PER_VERTEX);
        setCoords(squareCoords);
        setDrawOrder(drawOrder);
        setColor(color);
        setShaderCode(vertexShaderCode,fragmentShaderCode);
    }
/*
    public void draw(float[] mvpMatrix){
        float[] textureCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f,
        };
    }
    */

}