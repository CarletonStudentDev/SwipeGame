package com.example.owner.gameproject;

import android.content.Context;

public class Card {

    private Image image;
    private Square square;
    private Line line;
    private Circle circle;
    private float[] mMVPMatrix;
    private float[] Coords;
    private float[] color;
    private short[] drawOrder;
    private float width = 0.2f;
    private float length = 0.5f;
    private float radius = 0.1f;

    public Card (Context context, float[] mvpMatrix, int resourceId, float x, float y) {
        this.mMVPMatrix = mvpMatrix;

        image = new Image(context,resourceId, width,  0.3f, 0.3f);

        color = new float[] { 192f/255f, 39f/255f, 60/255f, 1.0f};
        square = new Square (context, width, length, x, y, color);

        width = width/2;
        length = length/2;
        Coords = new float[]{
                x + width,  y + length,
                x + width,  y - length,
                x - width,  y - length,
                x - width,  y + length,};

        drawOrder = new short[]{0,1,1,2,2,3,3,0};
        line = new Line(context,Coords,drawOrder);
        circle = new Circle(context,radius,0.0f,0.0f,color);
    }

    public void draw(){
        square.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
        image.draw(mMVPMatrix);
        circle.draw(mMVPMatrix);
    }
}
