package com.example.owner.gameproject;

import android.content.Context;

public class Card extends DrawableObject {

    public Square square;
    private float[] squareColor;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Context context, float x, float y, int colorId) {
        squareColor = GraphicsHelper.RGBArray(context, colorId);
        square = new Square (context, x, y, width, length, squareColor);
    }

    public void move(float x1, float y1){
        this.x = x1;
        this.y = y1;
    }

    public void draw(float[] mMVPMatrix){
        square.draw(mMVPMatrix);
        x = square.x;
        y = square.y;
    }

    public boolean inShape(float x, float y){
        return (x >= this.x - width) &&
               (x <= this.x + width) &&
               (y >= this.y - length) &&
               (y <= this.y + length);
    }
}
