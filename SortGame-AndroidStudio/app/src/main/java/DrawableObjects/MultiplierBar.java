package DrawableObjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.GraphicsHelper;
import OpenGL.Image;
import OpenGL.Square;

/**
 * Created by zack on 12/12/14.
 */
public class MultiplierBar extends DrawableObject
{

    private Square backgroundSquare;
    private int multiplierTimes = 5;
    private int numFull = 0;
    private int multiplier = 0;
    private Square[] blueProgressSquares = new Square[multiplierTimes];
    private Square[] redProgressSquares = new Square[multiplierTimes];
    private Square[] greenProgressSquares = new Square[multiplierTimes];
    private Square[] purpleProgressSquares = new Square[multiplierTimes];
    private Image multiplierNumber2;
    private Image multiplierNumber4;
    private Image multiplierNumber8;
    public float x,y;

    public MultiplierBar(Context context, float x, float y) {
        this.x=x;
        this.y=y;

        float backgroundColor[] = GraphicsHelper.RGBArray(context, R.color.lightGrey);
        float blueProgressColor[] = GraphicsHelper.RGBArray(context, R.color.blue);
        float redProgressColor[] = GraphicsHelper.RGBArray(context, R.color.red);
        float greenProgressColor[] = GraphicsHelper.RGBArray(context, R.color.green);
        float purpleProgressColor[] = GraphicsHelper.RGBArray(context, R.color.purple);


        // initialize shapes
        backgroundSquare = new Square(x + 0.1f, y, 1.1f, 0.105f, backgroundColor);
        float width = 1.1f / multiplierTimes;


        for(int i = 0;i < multiplierTimes;i++){
            blueProgressSquares[i] = new Square(x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, blueProgressColor);
        }
        for(int i = 0;i < multiplierTimes;i++){
            redProgressSquares[i] = new Square(x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, redProgressColor);
        }
        for(int i = 0;i < multiplierTimes;i++){
            greenProgressSquares[i] = new Square(x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, greenProgressColor);
        }
        for(int i = 0;i < multiplierTimes;i++){
            purpleProgressSquares[i] = new Square(x + 0.1f + (1.1f/2 + width / 2) - (i + 1) * width, y,width - 0.005f, 0.1f, purpleProgressColor);
        }


        multiplierNumber2 = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x2);
        multiplierNumber4 = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x4);
        multiplierNumber8 = new Image(context, x + 0.1f - (1.1f/2 + width / 2), y, 0.18f, R.drawable.x8);


    }
    @Override
    public void move(float x, float y) {

    }

    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        if(multiplier==0){
            for(int i = 0;i < numFull;i++) {
                blueProgressSquares[i].draw(mMVPMatrix);
            }
        }else if(multiplier==1){
            for(int i = 0;i < multiplierTimes;i++) {
                blueProgressSquares[i].draw(mMVPMatrix);
            }
            for(int i = 0;i < numFull;i++) {
                redProgressSquares[i].draw(mMVPMatrix);
            }
        }else if(multiplier==2){
            for(int i = 0;i < multiplierTimes;i++) {
                redProgressSquares[i].draw(mMVPMatrix);
            }
            for(int i = 0;i < numFull;i++) {
                greenProgressSquares[i].draw(mMVPMatrix);
            }
        }else if(multiplier==3){
            for(int i = 0;i < multiplierTimes;i++) {
                greenProgressSquares[i].draw(mMVPMatrix);
            }
            for(int i = 0;i < numFull;i++) {
                purpleProgressSquares[i].draw(mMVPMatrix);
            }
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

    public int giveMulti(){
        if(multiplier == 0) return 1;
        else if(multiplier == 1) return 2;
        else if(multiplier == 2) return 4;
        else return 8;
    }
}
