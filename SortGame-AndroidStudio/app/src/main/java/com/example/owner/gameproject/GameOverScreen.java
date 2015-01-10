package com.example.owner.gameproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by erickadbay on 15-01-09.
 */
public class GameOverScreen extends DrawableObject{

    private Square backgroundSquare;
    public float x,y;
    Resources resources;

    public GameOverScreen(Resources resources, float x, float y) {
        this.resources=resources;
        this.x=x;
        this.y=y;

        float backgroundColor[] = GraphicsHelper.RGBArray(resources, R.color.darkBlue);
        backgroundSquare = new Square(x, y, 1.1f, 1.5f, backgroundColor);

    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
    }



}
