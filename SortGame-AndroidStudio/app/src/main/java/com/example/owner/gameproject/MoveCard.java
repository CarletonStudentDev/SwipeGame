package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Varun on 4/3/2015.
 */
public class MoveCard {
    private float SIZE = 1.35f;
    private float SIZESCALE = 0.80f;

    private float xCoordinate1,
            yCoordinate1,
            xCoordinate2,
            yCoordinate2,
            xCenter,
            yCenter;

    private Paint color;

    public MoveCard(int colorID, float x, float y) {
        color = new Paint();
        Log.i("X:", Float.toString(x));
        Log.i("Y:", Float.toString(y));
        xCenter = x;
        yCenter = y;
        color.setColor(colorID);
    }

    public void updateCoords(float moveScaleX, float moveScaleY) {




        if (color.getColor() == ColorsLoader.loadColorByName("blue")) {
            xCenter -= moveScaleX;
            yCenter += moveScaleY;

        }

        if (color.getColor() == ColorsLoader.loadColorByName("red")) {
            xCenter -= moveScaleX;
            yCenter -= moveScaleY;

        }

        if (color.getColor() == ColorsLoader.loadColorByName("purple")) {
            xCenter += moveScaleX;
           yCenter += moveScaleY;

        }

        if (color.getColor() == ColorsLoader.loadColorByName("green")) {
          xCenter += moveScaleX;
          yCenter -= moveScaleY;

        }
        this.scale(SIZESCALE);
        xCoordinate1 = xCenter - SIZE*GameView.WIDTH/6f;
        xCoordinate2 = xCenter + SIZE*GameView.WIDTH/6f;
        yCoordinate1 = yCenter - SIZE*GameView.WIDTH/6f;
        yCoordinate2 = yCenter + SIZE*GameView.WIDTH/6f;

    }

    public void draw(Canvas canvas) {
        canvas.drawRect(xCoordinate1, yCoordinate1, xCoordinate2, yCoordinate2, color);
        this.updateCoords(30f * 0.275f, 30f * 0.338f);
    }

    public void scale(float scale) {
        if (scale != 1.0f) {
            SIZE = (SIZE *scale) + 0.06f;
        }
    }

    public boolean inQuadrant(){
        float x1 = 0.162f * GameView.WIDTH;
        float y1 = 0.9f * GameView.HEIGHT;
        float x2 = 0.838f * GameView.WIDTH;
        float y2 = 0.35f * GameView.HEIGHT;

        if((xCenter < x1 && yCenter < y2) ||
           (xCenter > x2 && yCenter < y2) ||
           (xCenter < x1 && yCenter > y1) ||
           (xCenter > x2 && yCenter > y1)){
            return true;
        }

        return false;
    }

    public float getXCenter(){
        return xCenter;
    }
    public float getYCenter(){
        return yCenter;
    }

}