package com.example.owner.gameproject;

/**
 * Game contains all the rules and information
 * about playing the card game.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2015-03-12
 *
 */

public class Game
{

    /**
     * DEFAULTLIVES: integer constant value representing the default number
     *               of lives present at beginning of the game.
     *
     */

    private static final int DEFAULTLIVES = 3;


    /**
     * DEFAULTSCORE: long constant value representing the default score
     *               value at beginning of the game.
     *
     * BASESCORE: long constant value representing the base amount the
     *            score will increment by.
     *
     */

    private static final long DEFAULTSCORE = 0L,
                              BASESCORE = 100L;


    /**
     * lives: integer value representing the amount of lives in the game.
     *
     */

    private int lives;


    /**
     * score: long value representing the score of the game.
     *
     */

    private long score;


    /**
     * Constructor for the Game class.
     *
     */

    public Game()
    {
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
    }


    /**
     * Getter for the amount of lives in the game.
     *
     * @return lives: integer value representing the amount of
     *                lives in the game.
     *
     */

    public int getLives()
    {
        return this.lives;
    }


    /**
     * Getter for the score of the game.
     *
     * @return score: long value representing the score of the game.
     *
     */

    public long getScore()
    {
        return this.score;
    }


    /**
     * Increment the score and notify multiplier bar accordingly.
     *
     */

    public void correct()
    {
        this.score += BASESCORE;

    }


    /**
     * Decrement the lives and notify multiplier bar accordingly.
     *
     */

    public void incorrect()
    {
        this.lives --;

    }



}
