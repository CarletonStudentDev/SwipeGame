package com.example.owner.gameproject;

import android.content.Context;

/**
 * Created by ERIC on 2014-12-13.
 */
public class TopBar extends DrawableObject {

    private int numHearts = 3;
    private Image[] hearts = new Image[numHearts];
    private Square backgroundSquare;

    public TopBar(Context context, float x, float y, int fullHearts) {
        backgroundSquare = new Square(context, 0f, 0.9f, 0.65f * 2 , 0.2f, context.getResources().getColor(R.color.darkBlue));

        for(int i = 0;i < numHearts;i++){
            if(i<fullHearts){
                hearts[i] = new Image(context, x - i*0.13f, y, 0.11f, R.drawable.fullheart);
            }else{
                hearts[i] = new Image(context, x - i*0.13f, y, 0.11f, R.drawable.blankheart);
            }
        }
    }
    @Override
    public void move(float x, float y) {

    }
    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        for(int i = 0;i < numHearts;i++) {
            hearts[i].draw(mMVPMatrix);
        }
    }
}