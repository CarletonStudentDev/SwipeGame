package Model;

import junit.framework.TestCase;


/**
 * JUnit testing for CardGenerator class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */

public class CardGeneratorTest extends TestCase
{

    /**
     * Test generateCard method of CardGenerator class
     */
    public void testGenerateCard()
    {
        CardGenerator cardGenerator = new CardGenerator();
        CardGenerator cardGenerator1 = new CardGenerator(10);

        assertTrue(cardGenerator.generateCard() instanceof Card);
        assertTrue(cardGenerator1.generateCard() instanceof Card);

    }
}