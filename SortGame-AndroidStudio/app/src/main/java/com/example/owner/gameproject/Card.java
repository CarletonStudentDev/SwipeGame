package com.example.owner.gameproject;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by OWNER on 2014-11-17.
 */
public class Card extends Drawable{

    private static final int COORDINATES_PER_VERTEX = 2;

    private Square square;
    private Sprite sprite;
    private Line line;
    private Line line2;
    private Line line3;
    private Line line4;
    private float[] mMVPMatrix = new float[16];
    private float[] lineCoords;

    public Card (Context context, float[] mvpMatrix) {
        super(context, COORDINATES_PER_VERTEX, GLES20.GL_TRIANGLES);
        this.mMVPMatrix = mvpMatrix;
        square = new Square(context);
        lineCoords = new float[]{ 0.5f,  0.5f, 0.5f, -0.5f};
        line = new Line(context,lineCoords);
        lineCoords = new float[]{0.5f, -0.5f,  -0.5f, -0.5f};
        line2 = new Line(context,lineCoords);
        lineCoords = new float[]{ -0.5f, -0.5f, -0.5f, 0.5f};
        line3 = new Line(context,lineCoords);
        lineCoords = new float[]{-0.5f,  0.5f, 0.5f,  0.5f};
        line4 = new Line(context,lineCoords);
        sprite = new Sprite(context,R.drawable.red);
    }

    public void draw(float[] mvpMatrix){
        square.draw(mMVPMatrix);
        sprite.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
        line2.draw(mMVPMatrix);
        line3.draw(mMVPMatrix);
        line4.draw(mMVPMatrix);
    }
}
