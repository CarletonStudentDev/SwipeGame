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
 * @version 1.1
 * @since 2014-11-27
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
     *        the player.
     *
     */

    private long score;


    /**
     * Constructor for the Player class.
     *
     */

    public Player()
    {
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
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

    public long getScore()
    {
        return this.score;
    }


    /**
     * Mutator for the score of the Player.
     *
     * @param score: long representing the number to
     *               set the Player's score to.
     */

    public void setScore(long score)
    {
        this.score = score;
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

    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void roundsOver(GameEvent ge)
    {

    }
}
