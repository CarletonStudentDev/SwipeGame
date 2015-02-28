package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-27
 * Created by Robert on 27/02/2015.
 */
public class Card {

    private Paint paint;
    private GameView view;
    private int x = 0;
    private int y = 0;
    private float width = 0.5f;
    private float height = 0.5f;
    public Card(GameView view){
        paint = new Paint();
        paint.setColor(view.getResources().getColor(R.color.blue));
        this.view = view;
    }

    public void draw(Canvas canvas){
        x = view.getWidth()/2;
        y = view.getHeight()/2;
        canvas.drawRect(x - width*view.getWidth()/2, y - height*view.getWidth()/2,
                x + width*view.getWidth()/2, y + height*view.getWidth()/2, paint);
    }
}
