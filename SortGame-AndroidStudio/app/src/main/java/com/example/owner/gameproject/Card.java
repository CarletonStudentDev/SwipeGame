package com.example.owner.gameproject;

<<<<<<< HEAD
import android.content.res.Resources;
=======
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
>>>>>>> 033e825ae4fd1035bb3eef1c406650b0e8a0191f

public class Card extends DrawableObject {

    public Square square;
    private float[] squareColor;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Resources resources, float x, float y, int colorId) {
        squareColor = GraphicsHelper.RGBArray(resources, colorId);
        square = new Square (x, y, width, length, squareColor);
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
<<<<<<< HEAD
=======
=======
import android.content.res.Resources;

public class Card extends DrawableObject {

    public Square square;
    private float[] squareColor;
    private float width = 0.5f;
    private float length = 0.5f;
    public float x;
    public float y;

    public Card (Resources resources, float x, float y, int colorId) {
        squareColor = GraphicsHelper.RGBArray(resources, colorId);
        square = new Square (x, y, width, length, squareColor);
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
>>>>>>> OpenGL
>>>>>>> 033e825ae4fd1035bb3eef1c406650b0e8a0191f
