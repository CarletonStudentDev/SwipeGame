package com.example.owner.gameproject;

/**
 * Created by home on 29/11/2014.
 */
public abstract class DrawableObject {

    public abstract void move(float x, float y);

    public abstract void draw(float[] mMVPMatrix);
}
