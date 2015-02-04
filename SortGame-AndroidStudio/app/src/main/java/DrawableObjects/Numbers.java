package DrawableObjects;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.Matrix;

import com.example.owner.gameproject.R;

import OpenGL.*;

public class Numbers implements DrawableObject {

    private float x;
    private float y;
    private float digitShift;
    private float dynamicShift=0;
    private int digits;
    private int forcedDigits;
    private int color;
    private int size;
    private Image[] numbers = new Image[8];
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

    public int singleDigit;
    public int fullNumber;

    float[] scratch = new float[16];
    float[] oldMatrix = new float[16];

    private double frame = 0;

    public Numbers(Context context, float x, float y, int number, int digits, int forcedDigits, float numSize, float digitShift, int color) {
        this.x = x;
        this.y = y;
        this.fullNumber = number;
        this.digits=digits;
        this.size=digits;
        this.forcedDigits=forcedDigits;
        this.digitShift=digitShift;
        this.color=color;

        float[] textureCoords = {
                0f, 0f,
                0f, 1f,
                0.1f, 1f,
                0.1f, 0f};

        //COLOR=1 BLACK
        //COLOR=2 WHITE
        if(color==1){
            zeroImage = new Image(context, x, y, numSize, R.drawable.gameuispritesheet);
            zeroImage.setTextureCoords(textureCoords);
            oneImage = new Image(context, x, y, numSize, R.drawable.one);
            twoImage = new Image(context, x, y, numSize, R.drawable.two);
            threeImage = new Image(context, x, y, numSize, R.drawable.three);
            fourImage = new Image(context, x, y, numSize, R.drawable.four);
            fiveImage = new Image(context, x, y, numSize, R.drawable.five);
            sixImage = new Image(context, x, y, numSize, R.drawable.six);
            sevenImage = new Image(context, x, y, numSize, R.drawable.seven);
            eightImage = new Image(context, x, y, numSize, R.drawable.eight);
            nineImage = new Image(context, x, y, numSize, R.drawable.nine);
        }else if(color==2){
            zeroImage = new Image(context, x, y, numSize, R.drawable.zero);
            oneImage = new Image(context, x, y, numSize, R.drawable.one);
            twoImage = new Image(context, x, y, numSize, R.drawable.two);
            threeImage = new Image(context, x, y, numSize, R.drawable.three);
            fourImage = new Image(context, x, y, numSize, R.drawable.four);
            fiveImage = new Image(context, x, y, numSize, R.drawable.five);
            sixImage = new Image(context, x, y, numSize, R.drawable.six);
            sevenImage = new Image(context, x, y, numSize, R.drawable.seven);
            eightImage = new Image(context, x, y, numSize, R.drawable.eight);
            nineImage = new Image(context, x, y, numSize, R.drawable.nine);
        }

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
    @Override
    public boolean isTouched(float x, float y) {
        return false;
    }

    public void decrease(int subAmount) {
        fullNumber -= subAmount;
        refreshNumbersArray();
    }

    public void increase(int incAmount) {
        fullNumber += incAmount;
        refreshNumbersArray();
    }

    public int getFullNumber() {
        return fullNumber;
    }


    public void setFullNumber(int number) {
        fullNumber=number;
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

            if(singleDigit == 0){
                numbers[i-1] = zeroImage;
            }else if(singleDigit == 1){
                numbers[i-1] = oneImage;
            }else if(singleDigit == 2){
                numbers[i-1] = twoImage;
            }else if(singleDigit == 3){
                numbers[i-1] = threeImage;
            }else if(singleDigit == 4){
                numbers[i-1] = fourImage;
            }else if(singleDigit == 5){
                numbers[i-1] = fiveImage;
            }else if(singleDigit == 6){
                numbers[i-1] = sixImage;
            }else if(singleDigit == 7){
                numbers[i-1] = sevenImage;
            }else if(singleDigit == 8){
                numbers[i-1] = eightImage;
            }else if(singleDigit == 9){
                numbers[i-1] = nineImage;
            }

        }
    }

}
