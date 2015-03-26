package com.example.owner.gameproject;


import android.content.Context;

/**
 * Game contains all the rules and information
 * about playing the card game.
 *
 * @author Jeton Sinoimeri
 * @version 1.3
 * @since 2015-03-12
 *
 */

public class Game
{
    /**
     *  DEFAULTLIVES integer constant value representing the default number
     *               of lives present at beginning of the game.
     */
    private static final int DEFAULTLIVES = 10000;

    /**
     * DEFAULTSCORE long constant value representing the default score
     *              value at beginning of the game.
     *
     * BASESCORE long constant value representing the base amount the
     *           score will increment by.
     */
    private static final long DEFAULTSCORE = 0L,
                              BASESCORE = 100L;

    /**
     * lives integer value representing the amount of lives in the game.
     */
    private int lives;

    /**
     * score long value representing the score of the game.
     */
    private long score;

    /**
     *  multiplier Multiplier instance representing the multiplier of the game.
     */
    private Multiplier multiplier;

    /**
     * livesFinished boolean value representing whether the lives have finished
     */
    private boolean liveFinished;

    /**
     * vibrator for when a wrong answer occurs
     */
    private Vibrate v;


    /**
     * Constructor for the Game class.
     */
    public Game(Context appContext)
    {
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
        this.multiplier = new Multiplier();
        this.liveFinished = false;
        MediaSounds.initializeSoundPool();
        MediaSounds.setContext(appContext);
        MediaSounds.setVolume(1f);
        v = new Vibrate(appContext);
    }

    /**
     * Getter for the amount of lives in the game.
     *
     * @return lives integer value representing the amount of lives in the game.
     */
    public int getLives()
    {
        return this.lives;
    }


    /**
     * Getter for the score of the game.
     *
     * @return score long value representing the score of the game.
     */
    public long getScore()
    {
        return this.score;
    }


    /**
     * Getter for the current meter count.
     *
     * @return integer value representing the current meter count.
     */
    public int getBarNum()
    {
        return this.multiplier.getMultiplierBarNum();
    }


    /**
     * Getter for the current multiplier value.
     *
     * @return integer value representing the current multiplier value.
     */

    public int getMultiplierNum()
    {
        return this.multiplier.getMultiplier();
    }

    /**
     * Getter for the indicator of lives finished
     *
     * @return livesFinished bool value representing whether the lives have finished.
     */
    public boolean getLivesFinished()
    {
        return this.liveFinished;
    }

    /**
     * Increment the score and notify multiplier bar accordingly.
     */
    public void correct()
    {
        this.multiplier.correctMatch();
        this.score += BASESCORE * this.multiplier.getMultiplier();
        MediaSounds.loadPlaySound(R.raw.correct, 1, 2f);
    }


    /**
     * Decrement the lives and notify multiplier bar accordingly.
     */
    public void incorrect()
    {
        this.lives--;

        if(lives < 1)
            this.liveFinished = true;

        this.multiplier.incorrectMatch();
        MediaSounds.loadPlaySound(R.raw.wrong, 1, 2f);
        v.vibrate();
    }
}
