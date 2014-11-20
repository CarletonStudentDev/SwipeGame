package com.example.owner.gameproject;

import android.content.Context;

public class Card {

    private Image image;
    private Line line;
    private float[] mMVPMatrix;
    private float[] Coords;
    private short[] drawOrder;

    public Card (Context context, float[] mvpMatrix, int resourceId, float size, float x, float y) {
        this.mMVPMatrix = mvpMatrix;
        /*Coords = new float[]{ size + x,  size + y,
                            size + x, -size + y,
                            -size + x, -size + y,
                            -size + x,  size + y,};
        */
        //drawOrder = new short[]{0,1,1,2,2,3,3,0};
        image = new Image(context,resourceId, size, x, y);
        //line = new Line(context,Coords,drawOrder);
    }

    public void draw(){
        image.draw(mMVPMatrix);
        //line.draw(mMVPMatrix);
    }
}
