package com.example.owner.gameproject;

import android.graphics.Canvas;

/**
 * Class description
 *
 * @author Robert
 * @version version_number
 * @since 2015-02-27
 */
public class GameManager{

    private Card card;
    private GameView view;

    //Variables representing screen center coordinates in pixels
    public static float centerX;
    public static float centerY;
    
    //Variable representing X axis scale offset (float->pixel)
    public static float scaleX;
    
    //Boolean which is true only on first draw
    private boolean start = true;

    public GameManager(GameView gameview){
        card = new Card();
        view = gameview;
    }

    public void draw(){
        if(start){
            centerX = view.getWidth()/2;
            centerY = view.getHeight()/2;
            scaleX = centerX / 2;
            start = false;
        }
        card.draw();
    }
}
