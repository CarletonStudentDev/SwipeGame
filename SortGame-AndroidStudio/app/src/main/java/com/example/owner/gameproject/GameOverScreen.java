package com.example.owner.gameproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by erickadbay on 15-01-09.
 */
public class GameOverScreen extends DrawableObject{

    private Square backgroundSquare;
    private Square retrySquare;
    private Square menuSquare;
    public float x,y;
    Resources resources;

    private Image outoflives;
    private Image outoftime;
    private Image scoretitle;
    private Image cardspersectitle;
    private Image highscoretitle;
    public Score score;
    public Score cardspersecscore;
    public Score highscore;

    public GameOverScreen(Resources resources, float x, float y) {
        this.resources=resources;
        this.x=x;
        this.y=y;

        float backgroundColor[] = GraphicsHelper.RGBArray(resources, R.color.darkBlue);
        backgroundSquare = new Square(x, y, 1.1f, 1.5f, backgroundColor);

        outoflives = new Image(resources, x, y+0.55f, 1f, R.drawable.outoflives);
        outoftime = new Image(resources, x, y+0.5f, 1f, R.drawable.outoftime);

        scoretitle = new Image(resources, x, y+0.37f, 0.5f, R.drawable.score);
        score = new Score(resources,0,y+0.25f);

        highscoretitle = new Image(resources, x, y+0.08f, 0.9f, R.drawable.highscore);
        highscore = new Score(resources,0,y-0.07f);

        cardspersectitle = new Image(resources, 0.15f, y-0.25f, 0.8f, R.drawable.cardspersec);
        cardspersecscore = new Score(resources,-0.425f,y-0.25f);

        float backgroundColor2[] = GraphicsHelper.RGBArray(resources, R.color.blue);
        retrySquare = new Square(x+0.25f, y-0.575f, 0.42f, 0.2f, backgroundColor2);
        menuSquare = new Square(x-0.25f, y-0.575f, 0.42f, 0.2f, backgroundColor2);
    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        //outoflives.draw(mMVPMatrix);
        outoftime.draw(mMVPMatrix);
        scoretitle.draw(mMVPMatrix);
        score.draw(mMVPMatrix);
        highscoretitle.draw(mMVPMatrix);
        highscore.draw(mMVPMatrix);
        cardspersectitle.draw(mMVPMatrix);
        cardspersecscore.draw(mMVPMatrix);
        retrySquare.draw(mMVPMatrix);
        menuSquare.draw(mMVPMatrix);
    }



}
