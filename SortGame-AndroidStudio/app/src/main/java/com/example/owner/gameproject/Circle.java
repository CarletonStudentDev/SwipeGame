package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zack on 28/11/14.
 */
public class Circle extends Drawable{
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

    private int vertices = 360;
    private float radius;
    private float x;
    private float y;

    public Circle(Context context, float x, float y, float radius, float[] color){
        super(context, COORDINATES_PER_VERTEX, GLES20.GL_TRIANGLE_FAN);
        radius = radius / 2;
        this.radius = radius;
        this.x = x;
        this.y = y;

        setCoords(getCoords());
        setDrawOrder(getDrawOrder());
        setColor(color);
        setShaderCode(vertexShaderCode,fragmentShaderCode);
        initializeBuffers();
    }

    private short[] getDrawOrder(){
        List<Short> drawOrderList = new ArrayList<Short>();
        for(int i = 1;i < vertices;i ++){
            drawOrderList.add((short)0);
            drawOrderList.add((short)i);
            drawOrderList.add((short)(i + 1));
        }
        short[] drawOrder = new short[drawOrderList.size()];
        for(int i = 0;i < drawOrderList.size();i++){
            drawOrder[i] = drawOrderList.get(i);
        }
        return drawOrder;
    }

    protected float[] getCoords() {
        List<Float> circleCoordsList = new ArrayList<Float>();
        circleCoordsList.add(x);
        circleCoordsList.add(y);

        for (int i = 0; i < vertices; i++) {
            float percent = (i / (float) (vertices - 1));
            float rad = (float) (percent * 2 * Math.PI);

            circleCoordsList.add( x + radius * (float) (Math.cos(rad)) );
            circleCoordsList.add( y + radius * (float) (Math.sin(rad)) );
        }

        float[] circleCoords = new float[circleCoordsList.size()];
        for (int i = 0; i < circleCoordsList.size(); i++) {
            circleCoords[i] = circleCoordsList.get(i);
        }

        return circleCoords;
    }
}
