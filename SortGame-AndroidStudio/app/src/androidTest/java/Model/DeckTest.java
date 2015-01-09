package Model;

import junit.framework.TestCase;

import java.util.Stack;

/**
 * JUnit testing for Deck class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-12-25
 *
 */

public class DeckTest extends TestCase
{

    /**
     * deck: Deck instance representing the deck
     *
     */

    private Deck deck;


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

        this.deck = new Deck();
        this.game = new Game(new Player(), new Multiplier(), this.deck);
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

        this.deck = null;
        this.game = null;
        this.gameEvent = null;

    }


    /**
     * Tests the deckSize method of the Deck class
     *
     */

    public void testDeckSize()
    {
        assertEquals(0, this.deck.deckSize());

        Deck deck1 = new Deck(12);
        assertEquals(12, deck1.deckSize());

        deck1 = new Deck(0);
        assertEquals(0, deck1.deckSize());

        deck1 = new Deck(-10);
        assertEquals(0, deck1.deckSize());

    }


    /**
     * Tests the getDeck method of the Deck class
     *
     */

    public void testGetDeck()
    {
        assertTrue(this.deck.getDeck() instanceof Stack);

    }


    /**
     * Tests roundsOver method of the Deck class
     *
     */

    public void testRoundsOver()
    {
        this.deck.livesFinish(this.gameEvent);

        for (int i = 14; i > -1; i--)
        {
            this.deck.roundsOver(this.gameEvent);
            assertEquals(i, this.deck.deckSize());

        }

    }


    /**
     * Tests the correctMatch method of the Deck class
     *
     */

    public void testCorrectMatch()
    {

    }


    /**
     * Tests the timeOut method of the Deck class
     *
     */

    public void testTimeOut()
    {
        this.deck.livesFinish(this.gameEvent);

        for (int i = 14; i > -1; i--)
        {
            this.deck.timeOut(this.gameEvent);
            assertEquals(i, this.deck.deckSize());

        }


    }


    /**
     * Tests the livesFinished method of the Deck class
     *
     */

    public void testLivesFinish()
    {
        assertEquals(0, this.deck.deckSize());

        this.deck.livesFinish(this.gameEvent);

        assertEquals(15, this.deck.deckSize());

    }
}