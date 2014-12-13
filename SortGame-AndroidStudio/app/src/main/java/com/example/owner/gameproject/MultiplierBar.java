package com.example.owner.gameproject;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by zack on 12/12/14.
 */
public class MultiplierBar extends DrawableObject {
    private Square backgroundSquare;
    private int multiplierTimes = 5;
    private int numFull;
    private Square[] greenSquare = new Square[multiplierTimes];
    private Image multiplierNumber;
    public MultiplierBar(Context context, float x, float y, int numFull, int multiplier) {
        this.numFull = numFull;
        backgroundSquare = new Square(context, 1.1f, 0.105f, x + 0.1f, y, context.getResources().getColor(R.color.lightGrey));
        float width = 1.1f / multiplierTimes;
        for(int i = 0;i < numFull;i++){
            greenSquare[i] = new Square(context, width - 0.005f, 0.1f, x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y, context.getResources().getColor(R.color.blue));
        }
        if(multiplier==2){
            multiplierNumber = new Image(context, R.drawable.x2, 0.18f, x + 0.1f - (1.1f/2 + width / 2), y);
        }else if (multiplier==4){
            multiplierNumber = new Image(context, R.drawable.x4, 0.18f, x + 0.1f - (1.1f/2 + width / 2), y);
        }else if (multiplier==8){
            multiplierNumber = new Image(context, R.drawable.x8, 0.18f, x + 0.1f - (1.1f/2 + width / 2), y);
        }
    }
    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        for(int i = 0;i < numFull;i++) {
            greenSquare[i].draw(mMVPMatrix);
        }
        multiplierNumber.draw(mMVPMatrix);
    }
}
