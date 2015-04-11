package com.example.owner.gameproject;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by OWNER on 2015-04-01.
 */
public class Stroop {

    private final boolean impossible;
    private TextObject word, lastWord;

    public String blueString="Blue",redString="Red",greenString="Green",purpleString="Purple",yellowString="Yellow",orangeString="Orange";

    private int color,lastColor=0,num,lastNum=0;

    private float centerX = (600f/1080)*GameView.WIDTH;

    /**
     * Constructor for the Stroop class.
     *
     */

    public Stroop(boolean impossible)
    {
        this.impossible = impossible;
        this.word = new TextObject(redString, (1200/1080)*GameView.WIDTH, (1100f/1701)*GameView.HEIGHT, GameView.typeface, ColorsLoader.loadColorByName("green"), (250f/1080) * GameView.WIDTH);
        randomColorString();
    }

    public void randomColorString() {


        num = getRandValue(1,7);
        while(num==lastNum)
            num = getRandValue(1,7);

        if(num==1)
            word.setText(blueString);
        else if(num==2)
            word.setText(redString);
        else if(num==3)
            word.setText(greenString);
        else if(num==4)
            word.setText(purpleString);
        else if(num==5)
            word.setText(yellowString);
        else if(num==6)
            word.setText(orangeString);
        lastNum = num;

        color = getRandValue(1,5);
        while(color==num || color==lastColor)
            color = getRandValue(1,5);

        if(color==1)
            word.setColor(ColorsLoader.loadColorByName("blue"));
        else if(color==2)
            word.setColor(ColorsLoader.loadColorByName("red"));
        else if(color==3)
            word.setColor(ColorsLoader.loadColorByName("green"));
        else if(color==4)
            word.setColor(ColorsLoader.loadColorByName("purple"));

        lastColor=color;
    }


    public void correctStroop(){
        lastWord = word;
        this.word = new TextObject(redString, (1200/1080)*GameView.WIDTH, (1100f/1701)*GameView.HEIGHT, GameView.typeface, ColorsLoader.loadColorByName("green"), (250f/1080) * GameView.WIDTH);
        randomColorString();
        word.setXcoordinate(2000f);
    }

    /**
     * Gets a random integer using random.nextInt method.
     *
     * @see java.util.Random
     *
     * @param low integer value representing the lowest
     *             value to start generating. Inclusive.
     *
     * @param high integer value representing the highest
     *              value to end generating. Exclusive.
     *
     * @return integer value representing the random integer generated by the
     *         random.nextInt method.
     *
     */
    private int getRandValue(int low, int high)
    {
        Random random = new Random();

        int randomInt = random.nextInt(high-low) + low;

        return randomInt;
    }

    /**
     * Draws to android's canvas.
     *
     * @see android.graphics.Canvas
     * @param canvas Canvas instance representing android.graphics.Canvas class.
     */

    public void draw(Canvas canvas)
    {

        if (word.getXcoordinate() > centerX)
            word.setXcoordinate(word.getXcoordinate() - (85f / 1080) * GameView.WIDTH);

        if (lastWord != null) {
            if (lastWord.getXcoordinate() > -500)
                lastWord.setXcoordinate(lastWord.getXcoordinate() - (85f / 1080) * GameView.WIDTH);
            this.lastWord.draw(canvas);
        }

        this.word.draw(canvas);
    }

    public int getColorId()
    {
        return word.getPaint().getColor();
    }



}

