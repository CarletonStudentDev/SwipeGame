package com.example.owner.gameproject;

/**
 * Created by home on 29/11/2014.
 */
public abstract class DrawableObject {

    float x;
    float y;

    abstract void move(float x, float y);

    abstract void draw();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
