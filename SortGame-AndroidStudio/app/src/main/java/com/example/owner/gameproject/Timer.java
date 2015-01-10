package com.example.owner.gameproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;

/**
 * Created by OWNER on 2015-01-09.
 */
public class Timer  extends DrawableObject {

    private Image[] score = new Image[7];

    private int digits = 2;
    public int num;
    public int currentTime = 10;
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


    public Timer(Resources resources, float x, float y ) {
        this.x = x;
        this.y = y;
        this.resources = resources;

        zero = BitmapFactory.decodeResource(resources, R.drawable.zeroblack);
        one = BitmapFactory.decodeResource(resources, R.drawable.oneblack);
        two = BitmapFactory.decodeResource(resources, R.drawable.twoblack);
        three = BitmapFactory.decodeResource(resources, R.drawable.threeblack);
        four = BitmapFactory.decodeResource(resources, R.drawable.fourblack);
        five = BitmapFactory.decodeResource(resources, R.drawable.fiveblack);
        six = BitmapFactory.decodeResource(resources, R.drawable.sixblack);
        seven = BitmapFactory.decodeResource(resources, R.drawable.sevenblack);
        eight = BitmapFactory.decodeResource(resources, R.drawable.eightblack);
        nine = BitmapFactory.decodeResource(resources, R.drawable.nineblack);


        zeroImage = new Image(resources, x, y, 0.15f, R.drawable.zeroblack);
        oneImage = new Image(resources, x, y, 0.15f, R.drawable.oneblack);
        twoImage = new Image(resources, x, y, 0.15f, R.drawable.twoblack);
        threeImage = new Image(resources, x, y, 0.15f, R.drawable.threeblack);
        fourImage = new Image(resources, x, y, 0.15f, R.drawable.fourblack);
        fiveImage = new Image(resources, x, y, 0.15f, R.drawable.fiveblack);
        sixImage = new Image(resources, x, y, 0.15f, R.drawable.sixblack);
        sevenImage = new Image(resources, x, y, 0.15f, R.drawable.sevenblack);
        eightImage = new Image(resources, x, y, 0.15f, R.drawable.eightblack);
        nineImage = new Image(resources, x, y, 0.15f, R.drawable.nineblack);

        //THIS IS FOR NORMAL MODE ONLY
        score[0] = zeroImage;
        score[1] = oneImage;
    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        this.oldMatrix = mMVPMatrix;
        this.scratch = mMVPMatrix;

        digitShift = 0.12f;

        for(int i = 0;i < digits;i++) {

            float[] scratch = new float[16];
            Matrix.setIdentityM(score[i].mModelMatrix, 0);
            Matrix.translateM(score[i].mModelMatrix, 0, digitShift * ((float) i), 0f, 0f);
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, score[i].mModelMatrix, 0);

            score[i].draw(scratch);
        }
    }

    public void reduceTime(int subAmount){
        currentTime -= subAmount;

        int calcScore = currentTime;



        for(int i = 1;i <= digits;i++) {
            if(i==1){
                num = currentTime;
                if(num>9){
                    num = num - ((currentTime/10)*10);
                }
            }else{
                num = (currentTime/ (int) Math.pow(10,(i-1)));
                if(num>9) {
                    num = num - ((currentTime / (int) Math.pow(10, i)) * 10);
                }else{
                    num = num - ((currentTime/(int) Math.pow(10,i))*10);
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
