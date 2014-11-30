package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;

public class Image extends Square {

    private static String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform sampler2D u_Texture;" +
                    "varying vec2 v_TexCoordinate;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    //"   gl_FragColor = vColor;" +
                    "   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);" +
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


    private float[] color = { 192f/255f, 39f/255f, 60/255f, 1.0f};

    public Image(Context context, int resourceId, float size, float x, float y){
        super(context, size, size, x, y, Color.BLACK);
        setShaderCode(vertexShaderCode,fragmentShaderCode);
        setTexture(true, resourceId);
    }
}
