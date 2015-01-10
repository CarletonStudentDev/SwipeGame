package DrawableObjects;


import android.content.res.Resources;

import com.example.owner.gameproject.R;

import OpenGL.DrawableObject;
import OpenGL.GraphicsHelper;
import OpenGL.Square;


public class Card extends DrawableObject
{

    public Square square;
    private float[] squareColor;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Resources resources, float x, float y, int colorId) {

        colorId = this.getColor(colorId);
        squareColor = GraphicsHelper.RGBArray(resources, colorId);
        square = new Square (x, y, width, length, squareColor);
    }


    private int getColor(int colorId)
    {
        if (colorId == 1)
            return R.color.blue;

        else if (colorId == 2)
            return R.color.green;

        else if (colorId == 3)
            return R.color.red;


        return R.color.purple;


    }

    public void move(float x1, float y1){
        this.x = x1;
        this.y = y1;
    }

    public void draw(float[] mMVPMatrix){
        square.draw(mMVPMatrix);
        x = square.x;
        y = square.y;
    }

    public boolean inShape(float x, float y){
        return (x >= this.x - width) &&
               (x <= this.x + width) &&
               (y >= this.y - length) &&
               (y <= this.y + length);
    }
}
