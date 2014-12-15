package com.example.owner.gameproject;

import android.content.Context;

/**
 * Created by OWNER on 2014-12-14.
 */
public class GameBoard {
    private Circle circle;
    private Circle circle2;
    private Circle circle3;
    private Circle circle4;

    public GameBoard (Context context) {

        circle = new Circle(context, 0.4f, 0.35f, 0.35f, GraphicsHelper.RGBArray(context, R.color.red));
        circle2 = new Circle(context, -0.4f, 0.35f, 0.35f, GraphicsHelper.RGBArray(context, R.color.green));
        circle3 = new Circle(context, -0.4f, -0.75f, 0.35f, GraphicsHelper.RGBArray(context, R.color.purple));
        circle4 = new Circle(context, 0.4f, -0.75f, 0.35f, GraphicsHelper.RGBArray(context, R.color.blue));

    }

    public boolean redTouched(float x, float y){
        return (x >= 0f) && (y >= -0.2f);
    }

    public boolean greenTouched(float x, float y){
        return (x <= 0f) && (y >= -0.2f);
    }

    public boolean blueTouched(float x, float y){
        return (x >= 0f) && (y <= -0.2f);
    }

    public boolean purpleTouched(float x, float y){
        return (x <= 0f) && (y <= -0.2f);
    }

    public void draw(float[] mMVPMatrix){
        circle.draw(mMVPMatrix);
        circle2.draw(mMVPMatrix);
        circle3.draw(mMVPMatrix);
        circle4.draw(mMVPMatrix);

    }
}
