package com.example.owner.gameproject;

import android.content.Context;

public class Card {

    private Square square;
    private Image image;
    private Line line;
    private float[] mMVPMatrix = new float[16];
    private float[] Coords;
    private short[] drawOrder;

    public Card (Context context, float[] mvpMatrix) {
        this.mMVPMatrix = mvpMatrix;
        Coords = new float[]{ 0.5f,  0.5f,
                            0.5f, -0.5f,
                            -0.5f, -0.5f,
                            -0.5f,  0.5f,};
        square = new Square(context,Coords);
        drawOrder = new short[]{0,1,1,2,2,3,3,0};
        line = new Line(context,Coords,drawOrder);
        image = new Image(context,R.drawable.red);
    }

    public void draw(){
        square.draw(mMVPMatrix);
        image.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
    }
}
