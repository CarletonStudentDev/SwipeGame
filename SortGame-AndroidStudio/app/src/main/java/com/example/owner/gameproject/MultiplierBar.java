package com.example.owner.gameproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public MultiplierBar(Resources resources, float x, float y) {

        // colors used
        float backgroundColor[] = GraphicsHelper.RGBArray(resources, R.color.lightGrey);
        float progressColor[] = GraphicsHelper.RGBArray(resources, R.color.blue);

        // images used
        Bitmap x2 = BitmapFactory.decodeResource(resources, R.drawable.x2);
        Bitmap x4 = BitmapFactory.decodeResource(resources, R.drawable.x4);
        Bitmap x8 = BitmapFactory.decodeResource(resources, R.drawable.x8);

        // initialize shapes
        backgroundSquare = new Square(x + 0.1f, y, 1.1f, 0.105f, backgroundColor);
        float width = 1.1f / multiplierTimes;
        for(int i = 0;i < multiplierTimes;i++){
            progressSquares[i] = new Square(x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, progressColor);
        }

        multiplierNumber2 = new Image(resources, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, x2);
        multiplierNumber4 = new Image(resources, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, x4);
        multiplierNumber8 = new Image(resources, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, x8);

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
            if(multiplier<3){
                multiplier ++;
                numFull = 0;
            }
        }
    }

    public void reset () {
        numFull = 0;
        multiplier = 0;
    }
}
