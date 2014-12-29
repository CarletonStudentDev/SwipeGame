package Model;

import junit.framework.TestCase;


/**
 * JUnit testing for Multiplier class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-12-25
 *
 */

public class MultiplierTest extends TestCase
{

    /**
     * multiplier: Multiplier instance representing the multiplier
     *
     */

    private Multiplier multiplier;


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

        this.multiplier = new Multiplier();
        this.game = new Game(new Player(), this.multiplier, new Deck());
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

        this.multiplier = null;
        this.game = null;
        this.gameEvent = null;
    }


    /**
     * Tests the getMultiplier method of the Multiplier class
     *
     */

    public void testGetMultiplier()
    {

        for (int i = 20; i > -20; i--)
        {
            this.multiplier = new Multiplier(i);
            if (i > 1 && i < 16)
                assertEquals(i, this.multiplier.getMultiplier());

            else
                assertEquals(1, this.multiplier.getMultiplier());

        }

    }


    /**
     * Tests the correctMatch method of the Multiplier class
     *
     */

    public void testCorrectMatch()
    {
        assertEquals(1, this.multiplier.getMultiplier());

        for (int i = 2; i < 16; i *= 2)
        {
            for (int j = 0; j < 5; j++)
                this.multiplier.correctMatch(this.gameEvent);

            assertEquals(i, this.multiplier.getMultiplier());
        }

    }


    /**
     * Tests the incorrectMatch method of the Multiplier class
     *
     */

    public void testIncorrectMatch()
    {
        assertEquals(1, this.multiplier.getMultiplier());

        for (int i = 2; i < 12; i *= 2)
        {
            for (int j = 0; j < 5; j++)
                this.multiplier.correctMatch(this.gameEvent);

            assertEquals(i, this.multiplier.getMultiplier());
        }


        this.multiplier.incorrectMatch(this.gameEvent);

        assertEquals(1, this.multiplier.getMultiplier());

    }


    /**
     * Tests the timeOut method of the Multiplier class
     *
     */

    public void testTimeOut()
    {
        assertEquals(1, this.multiplier.getMultiplier());

        for (int i = 2; i < 12; i *= 2)
        {
            for (int j = 0; j < 5; j++)
                this.multiplier.correctMatch(this.gameEvent);

            assertEquals(i, this.multiplier.getMultiplier());
        }


        this.multiplier.timeOut(this.gameEvent);

        assertEquals(1, this.multiplier.getMultiplier());


    }


    /**
     * Tests the livesFinished method of the Multiplier class
     *
     */

    public void testLivesFinish()
    {

        assertEquals(1, this.multiplier.getMultiplier());

        for (int i = 2; i < 10; i *= 2)
        {
            for (int j = 0; j < 5; j++)
                this.multiplier.correctMatch(this.gameEvent);

            assertEquals(i, this.multiplier.getMultiplier());
        }


        this.multiplier.livesFinish(this.gameEvent);

        assertEquals(1, this.multiplier.getMultiplier());

    }
}