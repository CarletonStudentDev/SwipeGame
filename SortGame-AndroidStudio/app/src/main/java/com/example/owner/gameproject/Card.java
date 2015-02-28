package com.example.owner.gameproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-27
 * Created by Robert on 27/02/2015.
 */
public class Card{
    private Paint color;
    private float x;
    private float y;
    private float x2;
    private float y2;
    private float size = 1f;

    public Card(){
        color = new Paint();
        color.setColor(GameView.instance.getResources().getColor(R.color.blue));

    }

    public void pixelOffset(){
        x = GameManager.instance.centerX - (size * GameManager.instance.scale);
        y = GameManager.instance.centerY - (size * GameManager.instance.scale);
        x2 = GameManager.instance.centerX + (size * GameManager.instance.scale);
        y2 = GameManager.instance.centerY + (size * GameManager.instance.scale);

    }

    public void draw(){
        pixelOffset();
        GameLoopThread.canvas.drawRect(x, y, x2, y2, color);
    }
}
