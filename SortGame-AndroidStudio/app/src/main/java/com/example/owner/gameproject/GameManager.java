package com.example.owner.gameproject;

import android.graphics.Canvas;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-27
 * Created by Robert on 27/02/2015.
 */
public class GameManager{

    private Card card;
    private GameView view;

    public GameManager(GameView view){
        this.view = view;
        card = new Card(view);
    }

    public void draw(Canvas canvas){
        card.draw(canvas);
    }
}
