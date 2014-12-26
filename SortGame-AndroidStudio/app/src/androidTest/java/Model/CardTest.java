package Model;

import junit.framework.TestCase;


/**
 * JUnit testing for Card class
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */


public class CardTest extends TestCase
{

    /**
     * Tests getPatternAttribute method of the Card class
     *
     */

    public void testGetPatternAttribute()
    {
        for (int i = 0; i< 10; i ++)
        {
            Card card = new Card(i);
            assertEquals(i, card.getPatternAttribute());
        }

    }
}