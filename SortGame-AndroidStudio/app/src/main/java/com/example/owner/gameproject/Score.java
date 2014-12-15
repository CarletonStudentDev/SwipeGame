package com.example.owner.gameproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by ERIC on 2014-12-15.
 */
public class Score extends DrawableObject {

    private Image[] numbers = new Image[9];
    private Image[] score = new Image[7];
    private Bitmap zero;
    private Bitmap one;
    private Bitmap two;
    private Bitmap three;
    private Bitmap four;
    private Bitmap five;
    private int digits = 1;
    private int currentScore = 0;
    private Image oneImage;

    public float x;
    private float y;
    private Resources resources;


    public Score(Resources resources, float x, float y ) {
        this.x = x;
        this.y = y;
        this.resources = resources;

        zero = BitmapFactory.decodeResource(resources, R.drawable.zero);
        one = BitmapFactory.decodeResource(resources, R.drawable.one);
        two = BitmapFactory.decodeResource(resources, R.drawable.two);
        three = BitmapFactory.decodeResource(resources, R.drawable.three);
        four = BitmapFactory.decodeResource(resources, R.drawable.four);
        five = BitmapFactory.decodeResource(resources, R.drawable.five);

        oneImage = new Image(resources, x, y, 0.12f, one);

        numbers[0] = new Image(resources, x, y, 0.12f, zero);

        score[0] = numbers[0];
    }
    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {

        for(int i = 0;i < digits;i++) {
            score[i].draw(mMVPMatrix);
        }
    }

    public void addToScore( int addAmount){
        currentScore = currentScore + addAmount;

        int calcScore = currentScore;

        digits = 1;
        while(calcScore>=10){
            digits++;
            calcScore = calcScore/10;
        }

        for(int i = 1;i <= digits;i++) {
            int num = (currentScore/(10*i))%1;

            if(num == 0){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, zero);
            }else if(num == 1){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, one);
            }else if(num == 2){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, two);
            }else if(num == 3){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, three);
            }else if(num == 4){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, four);
            }else if(num == 5){
                score[i] = new Image(resources, x+(i*0.1f), y, 0.12f, five);
            }

            score[1] = oneImage;
        }
    }

}
