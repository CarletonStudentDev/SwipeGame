package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class Card extends DrawableObject {

    public Image image;
    public Square square;
    public Line line;
    private float[] Coords;
    private int squareColor;
    private int lineColor;
    private short[] drawOrder;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Context context, int resourceId, float x, float y) {

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

    public void move(float x1, float y1){
        this.x = x1;
        this.y = y1;

        Coords = new float[]{
                x1 + width,  y1 + length,
                x1 + width,  y1 - length,
                x1 - width,  y1 - length,
                x1 - width,  y1 + length,};

        square.setCoords(Coords);
        line.setCoords(Coords);
        image.setCoords(Coords);
    }

    public void draw(float[] mMVPMatrix){

        //square.draw(mMVPMatrix);
        image.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
        x = image.x;
        y = image.y;
    }

    public boolean inShape(float x, float y){
        return (x >= this.x - width) &&
               (x <= this.x + width) &&
               (y >= this.y - length) &&
               (y <= this.y + length);
    }
}
