package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;

public class Line extends Drawable{
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

    public Line(Context context, float[] lineCoords, short[] drawOrder, int color) {
        super(context, COORDINATES_PER_VERTEX, GLES20.GL_LINES);
        setCoords(lineCoords);
        setDrawOrder(drawOrder);
        setColor(color);
        setShaderCode(vertexShaderCode,fragmentShaderCode);
    }

}
