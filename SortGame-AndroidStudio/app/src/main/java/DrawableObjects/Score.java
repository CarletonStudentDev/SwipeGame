package DrawableObjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.Image;

/**
 * Created by ERIC on 2014-12-15.
 */
public class Score extends DrawableObject
{

    private Image[] score = new Image[7];
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
    private Context context;
    private float digitShift;
    float[] scratch = new float[16];
    float[] oldMatrix = new float[16];


    public Score(Context context, float x, float y ) {
        this.x = x;
        this.y = y;
        this.context = context;

        zeroImage = new Image(context, x, y, 0.12f, R.drawable.zero);
        oneImage = new Image(context, x, y, 0.12f, R.drawable.one);
        twoImage = new Image(context, x, y, 0.12f, R.drawable.two);
        threeImage = new Image(context, x, y, 0.12f, R.drawable.three);
        fourImage = new Image(context, x, y, 0.12f, R.drawable.four);
        fiveImage = new Image(context, x, y, 0.12f, R.drawable.five);
        sixImage = new Image(context, x, y, 0.12f, R.drawable.six);
        sevenImage = new Image(context, x, y, 0.12f, R.drawable.seven);
        eightImage = new Image(context, x, y, 0.12f, R.drawable.eight);
        nineImage = new Image(context, x, y, 0.12f, R.drawable.nine);

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
                if(num>9){

                    num = num - ((currentScore/(int) Math.pow(10,i))*(int) Math.pow(10,i-1));

                    num = num - ((currentScore/(int) Math.pow(10,i))*(int) Math.pow(10,i-(i-1)));
                    thirdDigit = num;

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
