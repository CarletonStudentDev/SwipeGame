package DrawableObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.GraphicsHelper;
import OpenGL.Image;
import OpenGL.Square;

/**
 * Created by ERIC on 2014-12-13.
 */
public class TopBar extends DrawableObject
{

    private int numHearts = 3;
    private Image[] hearts = new Image[numHearts];
    private Image[] filledHearts = new Image[numHearts];
    private Square backgroundSquare;
    private int fullHearts = 3;

    public TopBar(Resources resources, float x, float y ) {

        // colors used
        float backgroundColor[] = GraphicsHelper.RGBArray(resources, R.color.darkBlue);

        // images used
        Bitmap blankHeart = BitmapFactory.decodeResource(resources, R.drawable.blankheart);
        Bitmap fullHeart = BitmapFactory.decodeResource(resources, R.drawable.fullheart);

        // initialize shapes
        backgroundSquare = new Square(0f, 0.9f, 0.65f * 2 , 0.2f, backgroundColor);

        for(int i = 0;i < numHearts;i++){
           hearts[i] = new Image(resources, x - i*0.13f, y, 0.11f, blankHeart);
        }

        for(int j = 0;j < numHearts;j++){
            filledHearts[j] = new Image(resources, x - j*0.13f, y, 0.11f, fullHeart);
        }
    }
    @Override
    public void move(float x, float y) {

    }
    @Override
    public void draw(float[] mMVPMatrix) {
        backgroundSquare.draw(mMVPMatrix);
        for(int i = 0;i < numHearts;i++) {
            hearts[i].draw(mMVPMatrix);
        }
        for(int j = 0;j < fullHearts;j++) {
            filledHearts[j].draw(mMVPMatrix);
        }
    }

    public void decreaseHearts(){
        if (fullHearts -1 > -1)
            fullHearts--;

    }

    public void setFullHearts(int fullHearts)
    {
        if (fullHearts > 0 && fullHearts > 3)
            this.fullHearts = fullHearts;

        else
            this.fullHearts = 3;
    }
}