package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 03/12/2014.
 */
public class CircleImage extends Circle {

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
                    "}";;
    private float radius;
    int vertices = 360;

    public CircleImage(Context context, float radius, float x, float y, int resourceId){
        super(context, radius, x, y, GraphicsHelper.RGBArray(context,R.color.red));
        this.radius = radius;
        setShaderCode(vertexShaderCode,fragmentShaderCode);
        setTexture(true, resourceId);
        setTextureCoords(getTextureCoords());
        initializeBuffers();
    }

    protected float[] getTextureCoords() {
        List<Float> circleCoordsList = new ArrayList<Float>();
        circleCoordsList.add(radius);
        circleCoordsList.add(radius);

        for (int i = 0; i < vertices; i++) {
            float percent = (i / (float) (vertices - 1));
            float rad = (float) (percent * 2 * Math.PI);

            circleCoordsList.add( -(radius * (float) (Math.cos(rad)) - radius) );
            circleCoordsList.add( -(radius * (float) (Math.sin(rad)) - radius) );
        }

        float[] circleCoords = new float[circleCoordsList.size()];
        for (int i = 0; i < circleCoordsList.size(); i++) {
            circleCoords[i] = circleCoordsList.get(i);
        }

        return circleCoords;
    }
}
