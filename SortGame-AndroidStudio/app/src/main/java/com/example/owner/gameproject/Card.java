package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;

public class Card implements DrawableObject{

    private Image image;
    private Square square;
    private Line line;
    private float[] mMVPMatrix;
    private float[] Coords;
    private int squareColor;
    private int lineColor;
    private short[] drawOrder;
    private float width = 0.5f;
    private float length = 0.5f;

    public Card (Context context, float[] mvpMatrix, int resourceId, float x, float y) {
        this.mMVPMatrix = mvpMatrix;

        image = new Image(context,resourceId, width,  x, y);

        squareColor = context.getResources().getColor(R.color.lightRed);
        lineColor = Color.BLACK;
        square = new Square (context, width, length, x, y, squareColor);

        width = width/2;
        length = length/2;
        Coords = new float[]{
                x + width,  y + length,
                x + width,  y - length,
                x - width,  y - length,
                x - width,  y + length,};

        drawOrder = new short[]{0,1,1,2,2,3,3,0};
        line = new Line(context, Coords, drawOrder, lineColor);
    }

    public void move(float x, float y){
        Coords = new float[]{
                x + width,  y + length,
                x + width,  y - length,
                x - width,  y - length,
                x - width,  y + length,};

        square.setCoords(Coords);
        line.setCoords(Coords);
        image.setCoords(Coords);
    }

    public void draw(){
        square.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
        image.draw(mMVPMatrix);
    }
}
