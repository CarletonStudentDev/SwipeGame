package com.example.owner.gameproject;

/**
 * GameManager class manages the logic of the game.
 *
 * @author Robert
 * @version 1.2
 * @since 2015-02-27
 *
 */

public class GameManager{

    private Card card;
    private GameView view;
    private GameBoard gameBoard;

    //Variables representing screen center coordinates in pixels
    public static float centerX;
    public static float centerY;
    
    //Variable representing X axis scale offset (float->pixel)
    public static float scaleX;

    public static float scaleY;
    
    //Boolean which is true only on first draw
    private boolean start = true;

    private int t;


    public GameManager(GameView gameview){
        card = new Card();
        gameBoard = new GameBoard();
        view = gameview;

        t = 0;
    }

    public void draw(){
        if(start){
            centerX = view.getWidth() / 2;
            centerY = view.getHeight() / 2;
            scaleX = centerX / 2;
            scaleY = centerY / 2;
            start = false;
        }

        // temporary to make card change color
        t ++;

        if (t % 100 == 0)
            card.generateNewColor();

        card.draw();
        gameBoard.draw();


    }
}
