package com.example.owner.gameproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;
import android.util.Log;

/**
 * Created by ERIC on 2014-12-15.
 */
public class Score extends DrawableObject {

    private Image[] numbers = new Image[9];
    private Image[] score = new Image[7];
    private int digits = 1;
    private int currentScore = 0;
    private Image zeroImage;
    private Image oneImage;
    private Image twoImage;
    private Image threeImage;
    private Image fourImage;
    private Image fiveImage;

    public float x;
    private float y;
    private Resources resources;


    public Score(Resources resources, float x, float y ) {
        this.x = x;
        this.y = y;
        this.resources = resources;

        Bitmap zero = BitmapFactory.decodeResource(resources, R.drawable.zero);
        Bitmap one = BitmapFactory.decodeResource(resources, R.drawable.one);
        Bitmap two = BitmapFactory.decodeResource(resources, R.drawable.two);
        Bitmap three = BitmapFactory.decodeResource(resources, R.drawable.three);
        Bitmap four = BitmapFactory.decodeResource(resources, R.drawable.four);
        Bitmap five = BitmapFactory.decodeResource(resources, R.drawable.five);

        zeroImage = new Image(resources, x, y, 0.12f, zero);
        oneImage = new Image(resources, x, y, 0.12f, one);
        twoImage = new Image(resources, x, y, 0.12f, two);
        threeImage = new Image(resources, x, y, 0.12f, three);
        fourImage = new Image(resources, x, y, 0.12f, four);
        fiveImage = new Image(resources, x, y, 0.12f, five);


        numbers[0] = new Image(resources, x, y, 0.12f, zero);

        score[0] = numbers[0];
    }
    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {

        float digitShift = -0.2f;

        for(int i = 0;i < digits;i++) {

            float[] scratch = new float[16];
            Matrix.translateM(score[i].mModelMatrix, 0, digitShift * i, 0f, 0f);
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, score[i].mModelMatrix, 0);

            digitShift = 0f;
            score[i].draw(scratch);
        }
    }

    public void addToScore(int addAmount, int multi){
        currentScore = currentScore + addAmount * multi;
        Log.i("currentScore", Integer.toString(currentScore));
        int calcScore = currentScore;

        digits = 1;
        while(calcScore>=10){
            digits++;
            calcScore = calcScore/10;
        }
        Log.i("calcScore", Integer.toString(calcScore));
        for(int i = 0;i <= digits;i++) {
            int num = (calcScore);
            Log.i("num", Integer.toString(num));

            switch (num) {
                case 0:
                    score[i] = zeroImage;
                    break;
                case 1:
                    score[i] = oneImage;
                    break;
                case 2:
                    score[i] = twoImage;
                    break;
                case 3:
                    score[i] = threeImage;
                    break;
                case 4:
                    score[i] = fourImage;
                    break;
                case 5:
                    score[i] = fiveImage;
                    break;
            }
        }
    }

}
