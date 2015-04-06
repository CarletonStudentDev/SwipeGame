package com.example.owner.gameproject;


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
    private static final int DEFAULTLIVES = 3;

    /**
     * DEFAULTSCORE long constant value representing the default score
     *              value at beginning of the game.
     *
     * BASESCORE long constant value representing the base amount the
     *           score will increment by.
     */
    private static final int DEFAULTSCORE = 0,
                              BASESCORE = 25;

    /**
     * lives integer value representing the amount of lives in the game.
     */
    private int lives;

    /**
     * score long value representing the score of the game.
     */
    private int score,highscore;

    /**
     *  multiplier Multiplier instance representing the multiplier of the game.
     */
    private Multiplier multiplier;

    /**
     * livesFinished boolean value representing whether the lives have finished
     */
    private boolean liveFinished;

    /**
     * Constructor for the Game class.
     */
    public Game()
    {
        //getHighScoreFromGoogle();
        //this.highscore = getHighScore();
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
        this.multiplier = new Multiplier();
        this.liveFinished = false;
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
    /*
    public long getHighScore()
    {
        return this.highscore;
    }*/

    public int getScore()
    {
        return this.score;
    }

    /*
    public void getHighScoreFromGoogle() {
        //GET HIGHSCORE FROM GOOGLE PLAY
        //Googlescore = ;

        //highscore = getLocalHighsScore();

        //if(highscore>Googlescore){
            //POST SCORE TO GOOGLE
        //}else{
            //highscore=Googlescore
        //}

        highscore = 9;//Test
    }*/

    /*
    private void checkNewScore(){
        if(score>highscore){
            highscore=score;
            //SET HIGHSCORE TO GOOGLE AND LOCAL

            
        }


    }*/

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
            //checkNewScore();

        this.multiplier.incorrectMatch();

        MediaSounds.loadPlaySound(R.raw.wrong, 1, 2f);
        Vibrate.vibrate();
    }
}
