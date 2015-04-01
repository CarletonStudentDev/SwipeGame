package Model;

import junit.framework.TestCase;
import java.util.Random;


/**
 * JUnit testing for Player class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */

public class PlayerTest extends TestCase
{

    /**
     * player: Player instance representing the player
     *
     */

    private Player player;


    /**
     * game: Game instance representing the game
     *
     */

    private Game game;


    /**
     * gameEvent: GameEvent representing the game event
     *
     */

    private GameEvent gameEvent;




    /**
     * @see junit.framework.TestCase
     *
     * @throws Exception indicating that there was an exception
     *         in initializing of the fields and resources
     *
     */

    public void setUp() throws Exception
    {
        super.setUp();

        this.player = new Player();
        this.game = new Game(this.player, new Multiplier(), new Deck());
        this.gameEvent = new GameEvent(this.game);
    }


    /**
     * @see junit.framework.TestCase
     *
     * @throws Exception indicating that there was an exception
     *         in deallocating of the fields and resources
     *
     */

    public void tearDown() throws Exception
    {
        super.tearDown();

        this.game = null;
        this.player = null;
        this.gameEvent = null;
    }


    /**
     * Tests getLives method of the Player class
     *
     */

    public void testGetLives()
    {
        assertEquals(3, this.player.getLives());

    }


    /**
     * Tests the setLives method of the Player class
     *
     */

    public void testSetLives()
    {
        assertEquals(3, this.player.getLives());

        Random random = new Random();


        for (int i = 0; i < 10; i++)
        {
            int highInt = random.nextInt(Integer.MAX_VALUE);
            int lowInt = random.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE;

            this.player.setLives(highInt);
            assertEquals(highInt, this.player.getLives());

            this.player.setLives(lowInt);
            assertEquals(3, this.player.getLives());
        }

    }


    /**
     * Tests getCurrentScore method of the Player class
     *
     */

    public void testGetCurrentScore()
    {
        assertEquals(0, this.player.getCurrentScore());
    }


    /**
     * Tests getHighScore method of the Player class
     *
     */

    public void testGetHighScore()
    {
        assertEquals(0, this.player.getHighScore());
    }


    /**
     * Tests setCurrentScore method of the Player class
     *
     */

    public void testSetCurrentScore()
    {
        assertEquals(0, this.player.getCurrentScore());

        Random random = new Random();

        for (int i = 0; i < 10; i++)
        {
            int highInt = random.nextInt(Integer.MAX_VALUE);
            int lowInt = random.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE;

            this.player.setCurrentScore(highInt);
            assertEquals(highInt, this.player.getCurrentScore());

            this.player.setCurrentScore(lowInt);
            assertEquals(0, this.player.getCurrentScore());
        }
    }


    /**
     * Tests correctMatch method of the Player class
     *
     */

    public void testCorrectMatch()
    {
        assertEquals(0, this.player.getCurrentScore());

        for (int i = 1; i < 10; i++)
        {
            this.player.correctMatch(this.gameEvent);
            assertEquals(i*100, this.player.getCurrentScore());
        }
    }


    /**
     * Tests incorrectMatch method of the Player class
     *
     */

    public void testIncorrectMatch()
    {
        assertEquals(3, this.player.getLives());

        for (int i = 2; i > -2; i--)
        {
            this.player.incorrectMatch(this.gameEvent);

            if (i > -1)
                assertEquals(i, this.player.getLives());

            else
                assertEquals(0, this.player.getLives());
        }

    }


    /**
     * Tests the timeOut method of the Player class
     *
     */

    public void testTimeOut()
    {
        assertEquals(3, this.player.getLives());

        for (int i = 2; i > -2; i--)
        {
            this.player.timeOut(this.gameEvent);

            if (i > -1)
                assertEquals(i, this.player.getLives());

            else
                assertEquals(0, this.player.getLives());
        }
    }


    /**
     * Tests the livesFinished method of the Player class
     *
     */

    public void testLivesFinish()
    {
        assertEquals(0, this.player.getCurrentScore());
        assertEquals(0, this.player.getHighScore());

        for (int i = 0; i < 5; i++)
        {
            this.player.correctMatch(this.gameEvent);
            this.player.incorrectMatch(this.gameEvent);
        }

        this.player.livesFinish(this.gameEvent);

        assertEquals(this.player.getCurrentScore(), this.player.getHighScore());

    }

}