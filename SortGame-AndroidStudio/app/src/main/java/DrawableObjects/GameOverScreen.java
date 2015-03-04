package DrawableObjects;

import android.content.Context;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.GraphicsHelper;
import OpenGL.Image;
import OpenGL.Square;
import OpenGL.TextManager;
import OpenGL.TextObject;

public class GameOverScreen implements DrawableObject {

    private static final int SCORENUM = 0,
                             HIGHNUM = 9999999,
                             CARDSPERSEC = 0;


    private Square backgroundSquare,
                   retrySquare,
                   menuSquare;

    private float x,
                  y;

    private Image outoflives,
                  outoftime,
                  scoretitle,
                  cardspersectitle,
                  highscoretitle,
                  dot,
                  retry,
                  back;


    private Numbers cardspersecscore;


    private TextObject score,
                       highscore;


    private TextManager textManager;

    private boolean isOutOfLives,
                    isTimedOut;


    public GameOverScreen(Context context, int textureHandle)
    {

        this.isOutOfLives = false;
        this.isTimedOut = false;

        this.x = 0;
        this.y = 0;

        float backgroundColor[] = GraphicsHelper.RGBArray(context, R.color.darkBlue);
        backgroundSquare = new Square(x, y, 1.1f, 1.5f, backgroundColor);

        outoflives = new Image(context, x, y+0.55f, 1f, R.drawable.outoflives);
        outoftime = new Image(context, x, y+0.5f, 1f, R.drawable.outoftime);

        scoretitle = new Image(context, x, y+0.37f, 0.5f, R.drawable.score);
        this.score = new TextObject("0", -0.35f, y+0.25f, 1.0f);
        //score = new Numbers(context,-0.35f,y+0.25f,SCORENUM,8,0,0.12f,0.1f,2);

        highscoretitle = new Image(context, x, y+0.05f, 0.9f, R.drawable.highscore);
        this.highscore = new TextObject("0", -0.35f, y-0.1f, 1.0f);
        //highscore = new Numbers(context,-0.35f,y-0.1f,HIGHNUM,8,0,0.12f,0.1f,2);

        cardspersectitle = new Image(context, 0.155f, y-0.3f, 0.8f, R.drawable.cardspersec);
        cardspersecscore = new Numbers(context,-0.45f,y-0.3f,CARDSPERSEC,3,3,0.12f,0.11f,2);
        dot = new Image(context, -0.285f, y-0.35f, 0.045f, R.drawable.dot);


        float backgroundColor2[] = GraphicsHelper.RGBArray(context, R.color.blue);
        retrySquare = new Square(x+0.25f, y-0.575f, 0.42f, 0.2f, backgroundColor2);
        menuSquare = new Square(x-0.225f, y-0.575f, 0.42f, 0.2f, backgroundColor2);
        retry = new Image(context, x+0.25f, y-0.575f, 0.2f, R.drawable.retry);
        back = new Image(context, x-0.225f, y-0.575f, 0.2f, R.drawable.back);

        this.textManager = new TextManager();
        this.textManager.setTextureID(textureHandle);
        this.textManager.addText(this.score);
        this.textManager.addText(this.highscore);
        this.textManager.setUniformscale(this.highscore.getTextSize());

    }

    public void setOutOfLives(boolean isOutOfLives)
    {
        this.isOutOfLives = isOutOfLives;
    }


    public void setTimedOut(boolean isTimedOut)
    {
        this.isTimedOut = isTimedOut;
    }



    @Override
    public void move(float x, float y)
    {

    }

    @Override
    public void draw(float[] mMVPMatrix)
    {

        backgroundSquare.draw(mMVPMatrix);

        if (isOutOfLives)
            outoflives.draw(mMVPMatrix);

        else if (isTimedOut)
            outoftime.draw(mMVPMatrix);

        scoretitle.draw(mMVPMatrix);
        this.textManager.draw(mMVPMatrix);
        //score.draw(mMVPMatrix);

        highscoretitle.draw(mMVPMatrix);
        //highscore.draw(mMVPMatrix);

        cardspersectitle.draw(mMVPMatrix);
        cardspersecscore.draw(mMVPMatrix);
        dot.draw(mMVPMatrix);

        retrySquare.draw(mMVPMatrix);
        menuSquare.draw(mMVPMatrix);
        retry.draw(mMVPMatrix);
        back.draw(mMVPMatrix);
    }

    public void updateScore(int scoreNum){
        this.score.setText(""+scoreNum);
    }

    public void updateHighScore(int scoreNum)
    {
        this.highscore.setText(""+scoreNum);
    }

    public boolean isTouched(float x, float y){
        return false; //For now
    }

}
