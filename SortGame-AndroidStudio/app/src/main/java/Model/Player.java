package Model;

import com.example.owner.gameproject.R;

/**
 * Player class contains the users score
 * and lives for the current game being
 * played.
 *
 * This class implements Listener.
 *
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.4
 * @since 2014-11-27
 *
 */

public class Player implements Listener
{

    /**
     * lives: integer representing the current lives
     *        of the player.
     *
     */

    private int lives;


    /**
     * score: long representing the current score of
     *        the Player.
     *
     * highScore: long representing the highest score
     *            the Player has achieved in the game.
     *
     */

    private long score,
                 highScore;


    /**
     * Constructor for the Player class.
     *
     */

    public Player()
    {
        this.lives = R.integer.DEFAULTLIVES;
        this.score = R.integer.DEFAULTSCORE;
        this.highScore = R.integer.DEFAULTSCORE;
    }


    /**
     * Getter for the lives of the Player
     *
     * @return lives: integer representing the current lives
     *                of the user.
     *
     */

    public int getLives()
    {
        return this.lives;
    }


    /**
     * Mutator for the lives of the Player.
     *
     * @param lives: integer representing the number to
     *               be set the Player's lives to. The
     *               parameter must be greater than -1
     *               otherwise the Player's lives are
     *               set to DefaultLives.
     *
     */

    public void setLives(int lives)
    {
        if(lives > R.integer.MINUSONE)
            this.lives = lives;

        else
            this.lives = R.integer.DEFAULTLIVES;
    }


    /**
     * Getter for the score of the Player.
     *
     * @return score: long representing the current
     *                score of the Player.
     *
     */

    public long getCurrentScore()
    {
        return this.score;
    }


    /**
     * Getter for the high score of the Player.
     *
     * @return highScore: long representing the highest
     *                    score the Player has achieved
     *                    in the game.
     *
     */

    public long getHighScore()
    {
        return this.highScore;
    }


    /**
     * Mutator for the score of the Player.
     *
     * @param score: long representing the number to
     *               set the Player's score to. The
     *               parameter must be greater than
     *               -1 otherwise the Player's score
     *               is set to DefaultScore.
     *
     */

    public void setCurrentScore(long score)
    {
        if (score > R.integer.MINUSONE)
            this.score = score;

        else
            this.score = R.integer.DEFAULTSCORE;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void correctMatch(GameEvent ge)
    {
        Game g = (Game)ge.getSource();

        Multiplier m = (Multiplier) g.getMultiplierMeter();

        this.score += R.integer.BASEPOINTS * m.getMultiplier();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void incorrectMatch(GameEvent ge)
    {
        if(this.lives - 1 > R.integer.MINUSONE)
            this.lives--;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void timeOut(GameEvent ge)
    {
        if(this.lives - 1 > R.integer.MINUSONE)
            this.lives--;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void livesFinish(GameEvent ge)
    {
        if (this.score > this.highScore)
            this.highScore = this.score;
    }

}
