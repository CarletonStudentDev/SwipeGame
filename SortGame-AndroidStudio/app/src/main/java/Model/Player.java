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
 * @version 1.6
 * @since 2014-11-27
 *
 */

public class Player implements Listener
{

    /**
     * DEFAULTLIVES: integer representing the default lives of
     *               the Player.
     *
     * BASEPOINTS: integer representing the base points for
     *             the calculation of the Player's score.
     *
     * DEFAULTSCORE: integer representing the default score of
     *               the Player at the start of each game.
     *
     */

    private static final int DEFAULTLIVES = 3,
                             BASEPOINTS = 100,
                             DEFAULTSCORE = 0;




    /**
     * lives: integer representing the current lives
     *        of the player.
     *
     * score: integer representing the current score of
     *        the Player.
     *
     * highScore: integer representing the highest score
     *            the Player has achieved in the game.
     *
     */

    private int lives,
                score,
                highScore;



    /**
     * Constructor for the Player class.
     *
     */

    public Player()
    {
        this.lives = DEFAULTLIVES;
        this.score = DEFAULTSCORE;
        this.highScore = DEFAULTSCORE;
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
        if(lives > -1)
            this.lives = lives;

        else
            this.lives = DEFAULTLIVES;
    }


    /**
     * Getter for the score of the Player.
     *
     * @return score: int representing the current
     *                score of the Player.
     *
     */

    public int getCurrentScore()
    {
        return this.score;
    }


    /**
     * Getter for the high score of the Player.
     *
     * @return highScore: int representing the highest
     *                    score the Player has achieved
     *                    in the game.
     *
     */

    public int getHighScore()
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

    public void setCurrentScore(int score)
    {
        if (score > -1)
            this.score = score;

        else
            this.score = DEFAULTSCORE;
    }


    /**
     * Mutator for the high score of the Player.
     *
     * @param highScore: integer value representing the high score
     *                   of the Player.
     *
     */

    public void setHighScore(int highScore)
    {
        this.highScore = highScore;
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

        this.score += BASEPOINTS * m.getMultiplier();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void incorrectMatch(GameEvent ge)
    {
        if(this.lives - 1 > -1)
            this.lives--;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void timeOut(GameEvent ge)
    {
        this.updateHighScore();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void livesFinish(GameEvent ge)
    {
        this.updateHighScore();
    }


    private void updateHighScore()
    {
        if (this.score > this.highScore)
            this.highScore = this.score;
    }


}
