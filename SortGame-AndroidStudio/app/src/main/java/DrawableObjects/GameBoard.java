package DrawableObjects;

import android.content.Context;
import android.content.res.Resources;

import com.example.owner.gameproject.R;

import OpenGL.Circle;
import OpenGL.GraphicsHelper;

/**
 * Created by OWNER on 2014-12-14.
 */
public class GameBoard {
    private Circle circle;
    private Circle circle2;
    private Circle circle3;
    private Circle circle4;

    public GameBoard (Context context) {

        // colors used
        float red[] = GraphicsHelper.RGBArray(context, R.color.red);
        float green[] = GraphicsHelper.RGBArray(context, R.color.green);
        float purple[] = GraphicsHelper.RGBArray(context, R.color.purple);
        float blue[] = GraphicsHelper.RGBArray(context, R.color.blue);

        // initialize shapes
        circle = new Circle(0.4f, 0.35f, 0.35f, red);
        circle2 = new Circle(-0.4f, 0.35f, 0.35f, green);
        circle3 = new Circle(-0.4f, -0.75f, 0.35f, purple);
        circle4 = new Circle(0.4f, -0.75f, 0.35f, blue);
    }

    /*
    public boolean redTouched(float x, float y){
        return (x >= 0f) && (y >= -0.2f);
    }

    public boolean greenTouched(float x, float y){
        return (x <= 0f) && (y >= -0.2f);
    }

    public boolean blueTouched(float x, float y){
        return (x >= 0f) && (y <= -0.2f);
    }

    public boolean purpleTouched(float x, float y){
        return (x <= 0f) && (y <= -0.2f);
    }
    */

    public int getQuadrant(float x, float y)
    {
        if (this.redTouched(x, y))
            return R.color.red;

        else if (this.blueTouched(x, y))
            return R.color.blue;

        else if (this.greenTouched(x, y))
            return R.color.green;

        else if (this.purpleTouched(x, y))
            return R.color.purple;

        return 0;
    }

    private boolean redTouched(float x, float y){
        return (x >= 0.1f) && (y >= 0.05f);
    }

    private boolean greenTouched(float x, float y){
        return (x <= -0.1f) && (y >= 0.05f);
    }

    private boolean blueTouched(float x, float y){
        return (x >= 0.1f) && (y <= -0.45f);
    }

    private boolean purpleTouched(float x, float y){
        return (x <= -0.1f) && (y <= -0.45f);
    }


    public int getGameOverButton(float x, float y)
    {
        if (this.retryTouched(x, y))
            return 1;

        else if (this.menuTouched(x, y))
            return 2;

        return 0;
    }

    private boolean retryTouched(float x, float y){
        return (x <= .4f && x> 0.04f) && (y <= -0.475f && y>-0.675f);
    }

    private boolean menuTouched(float x, float y){
        return (x <= -0.05f && x>-0.4f) && (y <= -0.475f && y>-0.675f);
    }


    public void draw(float[] mMVPMatrix){
        circle.draw(mMVPMatrix);
        circle2.draw(mMVPMatrix);
        circle3.draw(mMVPMatrix);
        circle4.draw(mMVPMatrix);
    }
}
