package com.example.owner.gameproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;

/**
 * Created by ERIC on 2014-12-15.
 */
public class Score extends DrawableObject {

    private Image[] score = new Image[7];
    private Bitmap zero;
    private Bitmap one;
    private Bitmap two;
    private Bitmap three;
    private Bitmap four;
    private Bitmap five;
    private Bitmap six;
    private Bitmap seven;
    private Bitmap eight;
    private Bitmap nine;
    private int digits = 1;
    public int num;
    public int thirdDigit;
    public int currentScore = 0;
    private Image zeroImage;
    private Image oneImage;
    private Image twoImage;
    private Image threeImage;
    private Image fourImage;
    private Image fiveImage;
    private Image sixImage;
    private Image sevenImage;
    private Image eightImage;
    private Image nineImage;

    public float x;
    private float y;
    private Resources resources;
    private float digitShift;
    float[] scratch = new float[16];
    float[] oldMatrix = new float[16];


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
        six = BitmapFactory.decodeResource(resources, R.drawable.six);
        seven = BitmapFactory.decodeResource(resources, R.drawable.seven);
        eight = BitmapFactory.decodeResource(resources, R.drawable.eight);
        nine = BitmapFactory.decodeResource(resources, R.drawable.nine);


        zeroImage = new Image(resources, x, y, 0.12f, zero);
        oneImage = new Image(resources, x, y, 0.12f, one);
        twoImage = new Image(resources, x, y, 0.12f, two);
        threeImage = new Image(resources, x, y, 0.12f, three);
        fourImage = new Image(resources, x, y, 0.12f, four);
        fiveImage = new Image(resources, x, y, 0.12f, five);
        sixImage = new Image(resources, x, y, 0.12f, six);
        sevenImage = new Image(resources, x, y, 0.12f, seven);
        eightImage = new Image(resources, x, y, 0.12f, eight);
        nineImage = new Image(resources, x, y, 0.12f, nine);

        score[0] = zeroImage;
    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        this.oldMatrix = mMVPMatrix;
        this.scratch = mMVPMatrix;

        digitShift = 0.1f;

        for(int i = 0;i < digits;i++) {

            float[] scratch = new float[16];
            Matrix.setIdentityM(score[i].mModelMatrix, 0);
            Matrix.translateM(score[i].mModelMatrix, 0, digitShift * ((float) i), 0f, 0f);
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, score[i].mModelMatrix, 0);

            score[i].draw(scratch);
        }
    }

    public void addToScore(int addAmount, int multi){
        currentScore += addAmount * multi;

        int calcScore = currentScore;

        digits = 1;
        while(calcScore>=10){
            digits++;
            calcScore = calcScore/10;
        }

        for(int i = 1;i <= digits;i++) {
            if(i==1){
                num = currentScore;
                if(num>9){
                    num = num - ((currentScore/10)*10);
                }
            }else{
                num = (currentScore/ (int) Math.pow(10,(i-1)));
                if(num>9) {
                    num = num - ((currentScore / (int) Math.pow(10, i)) * 10);
                }else{
                    num = num - ((currentScore/(int) Math.pow(10,i))*10);
                }
            }

            if(num == 0){
                score[i-1] = zeroImage;
            }else if(num == 1){
                score[i-1] = oneImage;
            }else if(num == 2){
                score[i-1] = twoImage;
            }else if(num == 3){
                score[i-1] = threeImage;
            }else if(num == 4){
                score[i-1] = fourImage;
            }else if(num == 5){
                score[i-1] = fiveImage;
            }else if(num == 6){
                score[i-1] = sixImage;
            }else if(num == 7){
                score[i-1] = sevenImage;
            }else if(num == 8){
                score[i-1] = eightImage;
            }else if(num == 9){
                score[i-1] = nineImage;
            }
        }
    }

}
