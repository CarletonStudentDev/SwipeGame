package Model;

import junit.framework.TestCase;


/**
 * JUnit testing for Game class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */

public class GameTest extends TestCase
{
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

        this.game = new Game(new Player(), new Multiplier(), new Deck());
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

        this.gameEvent = null;
        this.game = null;
    }


    /**
     * Tests the getMultiplierMeter method of the Game class
     *
     */

    public void testGetMultiplierMeter()
    {
        assertTrue(this.game.getMultiplierMeter() instanceof Multiplier);
    }


    /**
     * Tests the getPlayer method of the Game class
     *
     */

    public void testGetPlayer()
    {
        assertTrue(this.game.getPlayer() instanceof Player);
    }


    /**
     * Tests the getDeck method of the Game class
     *
     */

    public void testGetDeck()
    {
        assertTrue(this.game.getDeck() instanceof Deck);
    }


    /**
     * Tests the getActiveCard method of the Game class
     *
     */

    public void testGetActiveCard()
    {
        assertTrue(this.game.getActiveCard() == null);
    }


    /**
     * Tests the getTimedOut method of the Game class
     *
     */

    public void testGetTimedOut()
    {
        assertFalse(this.game.getTimedOut());
    }


    /**
     * Tests the setTimedOut method of the Game class
     *
     */

    public void testSetTimedOut()
    {
        assertFalse(this.game.getTimedOut());
        this.game.setTimedOut(true);
        assertTrue(this.game.getTimedOut());
    }


    /**
     * Tests addPile method of the Game class
     *
     */

    public void testAddPile()
    {
        Pile pile = new Pile(1);

        for (int i = 1; i < 10; i++)
        {
            this.game.addPile(pile);

            assertEquals(i, this.game.getPileArray().size());
        }

    }


    /**
     * Tests getPileArray method of the Game class
     *
     */

    public void testGetPileArray()
    {
        assertEquals(0, this.game.getPileArray().size());
    }


    /**
     * Tests the timeOut method of the Game class
     *
     */

    public void testTimeOut()
    {
        Player player = (Player)this.game.getPlayer();

        for (int i = 2; i > -1; i--)
        {
            this.game.timeOut();
            assertEquals(i, player.getLives());
            assertTrue(this.game.getTimedOut());
        }

    }


    /**
     * Tests the drawCard method of the Game class
     *
     */

    public void testDrawCard()
    {


    }
}