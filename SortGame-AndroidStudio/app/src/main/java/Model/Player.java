package Model;

/**
 * Player class contains the users score
 * and lives for the current game being
 * played.
 *
 * This class implements Listener.
 *
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.3
 * @since 2014-11-27git
 *
 */

public class Player implements Listener
{

    /**
     * DEFAULTLIVES: integer representing the default lives
     *               the player has at the start of the game.
     *
     */

    private static final int DEFAULTLIVES = 3;


    /**
     * DEFAULTSCORE: long representing the default score the
     *               player has at the start of the game.
     *
     */

    private static final long DEFAULTSCORE = 0L;


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
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
        this.highScore = this.score;
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
     *               be set the Player's lives to.
     *
     */

    public void setLives(int lives)
    {
        this.lives = lives;
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
     *               set the Player's score to.
     */

    public void setCurrentScore(long score)
    {
        if (score > -1)
            this.score = score;

        else
            this.score = DEFAULTSCORE;
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

        this.score += 100 * m.getMultiplier();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void incorrectMatch(GameEvent ge)
    {
        this.lives--;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void timeOut(GameEvent ge)
    {
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
