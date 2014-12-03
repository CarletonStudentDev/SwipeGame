package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class Card {

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
    private float x;
    private float y;

    public Card (Context context, float[] mvpMatrix, int resourceId, float x, float y) {
        this.mMVPMatrix = mvpMatrix;
        this.x = x;
        this.y = y;

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
        setX(x1);
        setY(y1);
        //this.x = x1;
        //this.y = y1;

        Coords = new float[]{
                x1 + width,  y1 + length,
                x1 + width,  y1 - length,
                x1 - width,  y1 - length,
                x1 - width,  y1 + length,};

        square.setCoords(Coords);
        line.setCoords(Coords);
        image.setCoords(Coords);
    }

    public void draw(){
        square.draw(mMVPMatrix);
        line.draw(mMVPMatrix);
        image.draw(mMVPMatrix);
    }

    public boolean inShape(float x, float y){
        return (x >= this.x - width) &&
               (x <= this.x + width) &&
               (y >= this.y - length) &&
               (y <= this.y + length);
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
}
