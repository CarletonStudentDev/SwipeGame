package com.example.owner.gameproject;

import android.content.res.Resources;
import android.opengl.Matrix;

public class Numbers extends DrawableObject {

    public float x;
    private float y;
    private Resources resources;
    private float digitShift;
    private float dynamicShift=0;
    private int digits;
    private int forcedDigits;
    private int color;
    private int size;
    private Image[] numbers = new Image[8];
    private Image blackZeroImage;
    private Image blackOneImage;
    private Image blackTwoImage;
    private Image blackThreeImage;
    private Image blackFourImage;
    private Image blackFiveImage;
    private Image blackSixImage;
    private Image blackSevenImage;
    private Image blackEightImage;
    private Image blackNineImage;
    private Image whiteZeroImage;
    private Image whiteOneImage;
    private Image whiteTwoImage;
    private Image whiteThreeImage;
    private Image whiteFourImage;
    private Image whiteFiveImage;
    private Image whiteSixImage;
    private Image whiteSevenImage;
    private Image whiteEightImage;
    private Image whiteNineImage;

    public int singleDigit;
    public int fullNumber;

    float[] scratch = new float[16];
    float[] oldMatrix = new float[16];


    public Numbers(Resources resources, float x, float y, int number, int digits, int forcedDigits, float numSize, float digitShift, int color) {
        this.x = x;
        this.y = y;
        this.resources = resources;
        this.fullNumber = number;
        this.digits=digits;
        this.size=digits;
        this.forcedDigits=forcedDigits;
        this.digitShift=digitShift;
        this.color=color;

        blackZeroImage = new Image(resources, x, y, numSize, R.drawable.zeroblack);
        blackOneImage = new Image(resources, x, y, numSize, R.drawable.oneblack);
        blackTwoImage = new Image(resources, x, y, numSize, R.drawable.twoblack);
        blackThreeImage = new Image(resources, x, y, numSize, R.drawable.threeblack);
        blackFourImage = new Image(resources, x, y, numSize, R.drawable.fourblack);
        blackFiveImage = new Image(resources, x, y, numSize, R.drawable.fiveblack);
        blackSixImage = new Image(resources, x, y, numSize, R.drawable.sixblack);
        blackSevenImage = new Image(resources, x, y, numSize, R.drawable.sevenblack);
        blackEightImage = new Image(resources, x, y, numSize, R.drawable.eightblack);
        blackNineImage = new Image(resources, x, y, numSize, R.drawable.nineblack);

        whiteZeroImage = new Image(resources, x, y, 0.12f, R.drawable.zero);
        whiteOneImage = new Image(resources, x, y, 0.12f, R.drawable.one);
        whiteTwoImage = new Image(resources, x, y, 0.12f, R.drawable.two);
        whiteThreeImage = new Image(resources, x, y, 0.12f, R.drawable.three);
        whiteFourImage = new Image(resources, x, y, 0.12f, R.drawable.four);
        whiteFiveImage = new Image(resources, x, y, 0.12f, R.drawable.five);
        whiteSixImage = new Image(resources, x, y, 0.12f, R.drawable.six);
        whiteSevenImage = new Image(resources, x, y, 0.12f, R.drawable.seven);
        whiteEightImage = new Image(resources, x, y, 0.12f, R.drawable.eight);
        whiteNineImage = new Image(resources, x, y, 0.12f, R.drawable.nine);


        refreshNumbersArray();
    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        this.oldMatrix = mMVPMatrix;
        this.scratch = mMVPMatrix;

        if(digits<size){
            dynamicShift = (size-digits)*0.05f;
        }
        for(int i = 0;i < digits;i++) {

            float[] scratch = new float[16];
            Matrix.setIdentityM(numbers[i].mModelMatrix, 0);
            Matrix.translateM(numbers[i].mModelMatrix, 0, (digitShift*((float) i))+dynamicShift, 0f, 0f);
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, numbers[i].mModelMatrix, 0);

            numbers[i].draw(scratch);
        }
    }

    public void decrease(int subAmount) {
        fullNumber -= subAmount;

        refreshNumbersArray();
    }

    public void increase(int incAmount) {
        fullNumber += incAmount;

        refreshNumbersArray();
    }

    public void refreshNumbersArray(){

        int calc = fullNumber;

        digits = 1;
        while(calc>=10){
            digits++;
            calc = calc/10;
        }
        if(forcedDigits>0){
            digits=forcedDigits;
        }
        for(int i = 1;i <= digits;i++) {
            if(i==1){
                singleDigit = fullNumber;
                if(singleDigit>9){
                    singleDigit -= ((fullNumber/10)*10);
                }
            }else{
                singleDigit = (fullNumber/ (int) Math.pow(10,(i-1)));
                if(singleDigit>9) {
                    singleDigit -= ((fullNumber/(int) Math.pow(10,i))*10);
                }else{
                    singleDigit -= ((fullNumber/(int) Math.pow(10,i))*10);
                }
            }

            //COLOR=1 BLACK
            //COLOR=2 WHITE
            if(color==1){
                if(singleDigit == 0){
                    numbers[i-1] = blackZeroImage;
                }else if(singleDigit == 1){
                    numbers[i-1] = blackOneImage;
                }else if(singleDigit == 2){
                    numbers[i-1] = blackTwoImage;
                }else if(singleDigit == 3){
                    numbers[i-1] = blackThreeImage;
                }else if(singleDigit == 4){
                    numbers[i-1] = blackFourImage;
                }else if(singleDigit == 5){
                    numbers[i-1] = blackFiveImage;
                }else if(singleDigit == 6){
                    numbers[i-1] = blackSixImage;
                }else if(singleDigit == 7){
                    numbers[i-1] = blackSevenImage;
                }else if(singleDigit == 8){
                    numbers[i-1] = blackEightImage;
                }else if(singleDigit == 9){
                    numbers[i-1] = blackNineImage;
                }
            }else if(color==2){
                if(singleDigit == 0){
                    numbers[i-1] = whiteZeroImage;
                }else if(singleDigit == 1){
                    numbers[i-1] = whiteOneImage;
                }else if(singleDigit == 2){
                    numbers[i-1] = whiteTwoImage;
                }else if(singleDigit == 3){
                    numbers[i-1] = whiteThreeImage;
                }else if(singleDigit == 4){
                    numbers[i-1] = whiteFourImage;
                }else if(singleDigit == 5){
                    numbers[i-1] = whiteFiveImage;
                }else if(singleDigit == 6){
                    numbers[i-1] = whiteSixImage;
                }else if(singleDigit == 7){
                    numbers[i-1] = whiteSevenImage;
                }else if(singleDigit == 8){
                    numbers[i-1] = whiteEightImage;
                }else if(singleDigit == 9){
                    numbers[i-1] = whiteNineImage;
                }
            }

        }
    }

}
