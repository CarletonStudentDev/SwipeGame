package com.example.owner.gameproject;

import android.content.Context;

/**
 * Created by zack on 12/12/14.
 */
public class MultiplierBar extends DrawableObject {
    private Square backgroundSquare;
    private int multiplierTimes = 5;
    private int numFull = 0;
    private int multiplier = 0;
    private Square[] progressSquares = new Square[multiplierTimes];
    private Image multiplierNumber2;
    private Image multiplierNumber4;
    private Image multiplierNumber8;

    public MultiplierBar(Context context, float x, float y) {

        backgroundSquare = new Square(context, x + 0.1f, y, 1.1f, 0.105f, GraphicsHelper.RGBArray(context, R.color.lightGrey));
        float width = 1.1f / multiplierTimes;
        for(int i = 0;i < multiplierTimes;i++){
            progressSquares[i] = new Square(context, x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, GraphicsHelper.RGBArray(context, R.color.blue));
        }

        multiplierNumber2 = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x2);
        multiplierNumber4 = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x4);
        multiplierNumber8 = new Image(context,  x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x8);

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

        if(multiplier==1){
            multiplierNumber2.draw(mMVPMatrix);
        }else if (multiplier==2){
            multiplierNumber4.draw(mMVPMatrix);
        }else if (multiplier==3){
            multiplierNumber8.draw(mMVPMatrix);
        }
    }

    public void increaseNumFull () {
        if(numFull<5){
            numFull++;
        }else {
            numFull = 0;
            if(multiplier<2){
                multiplier ++;
            }
        }
    }

    public void reset () {
        numFull = 0;
        multiplier = 0;
    }
}
