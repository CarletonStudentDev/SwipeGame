package Model;

import junit.framework.TestCase;


/**
 * JUnit testing for Pile class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */

public class PileTest extends TestCase
{

    /**
     * Tests checkCard method of Pile class
     *
     */

    public void testCheckCard()
    {
        Pile pile = new Pile(3);
        Card card = new Card(pile.getPatternAttribute());
        assertTrue(pile.checkCard(card));

        pile = new Pile(-1);
        card = new Card(pile.getPatternAttribute());
        assertTrue(pile.checkCard(card));

    }
}