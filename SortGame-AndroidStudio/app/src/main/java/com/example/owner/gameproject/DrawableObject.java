package com.example.owner.gameproject;

/**
 * Created by home on 29/11/2014.
 */
public interface DrawableObject {

    void move(float x, float y);

    void draw(float[] mMVPMatrix);
}
