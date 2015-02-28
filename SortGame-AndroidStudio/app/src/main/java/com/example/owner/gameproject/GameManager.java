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

    public static float centerX;
    public static float centerY;
    public static float scale;
    public static GameManager instance = null;

    private boolean start = true;


    public GameManager(GameView gameview){
        card = new Card();
        view = gameview;
        if(instance ==  null){
            instance = this;
        }
    }

    public void draw(){
        if(start){
            centerX = view.getWidth()/2;
            centerY = view.getHeight()/2;
            scale = centerX / 2;
            start = false;
        }
        card.draw();
    }
}
