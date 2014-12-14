package com.example.owner.gameproject;

import android.content.Context;

/**
 * Created by zack on 12/12/14.
 */
public class MultiplierBar extends DrawableObject {
    private Square backgroundSquare;
    private int multiplierTimes = 5;
    private int numFull;
    private Square[] progressSquares = new Square[multiplierTimes];
    private Image multiplierNumber;
    public MultiplierBar(Context context, float x, float y, int numFull, int multiplier) {
        this.numFull = numFull;
        backgroundSquare = new Square(context, x + 0.1f, y, 1.1f, 0.105f, GraphicsHelper.RGBArray(context, R.color.lightGrey));
        float width = 1.1f / multiplierTimes;
        for(int i = 0;i < numFull;i++){
            progressSquares[i] = new Square(context, x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, GraphicsHelper.RGBArray(context, R.color.blue));
        }
        if(multiplier==2){
            multiplierNumber = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x2);
        }else if (multiplier==4){
            multiplierNumber = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x4);
        }else if (multiplier==8){
            multiplierNumber = new Image(context,  x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x8);
        }
    }
    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        for(int i = 0;i < numFull;i++) {
            progressSquares[i].draw(mMVPMatrix);
        }
        multiplierNumber.draw(mMVPMatrix);
    }
}
