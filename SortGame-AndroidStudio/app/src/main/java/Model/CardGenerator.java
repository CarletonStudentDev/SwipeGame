package Model;

import java.util.Random;


/**
 * CardGenerator generates a Card with a
 * pattern attribute chosen at random.
 *
 * @author Varun Sriram
 * @version 1.1
 * @since 2014-11-28
 *
 */


public class CardGenerator
{

    /**
     * DEFAULTNUMPATTERNATTR: integer representing the default
     *                        value for the number of pattern
     *                        attributes.
     *
     */

    private static final int DEFAULTNUMPATTERNATTR = 4;


    /**
     * random: Random instance to be used to generate random
     *         numbers.
     *
     */

    private Random random;


    /**
     * numOfPatternAttr: integer value representing the number of
     *                   pattern attributes that will be used to
     *                   generate different cards.
     */

    private int numOfPatternAttr;


    /**
     * Default constructor for CardGenerator class.
     *
     */

    public CardGenerator()
    {
        this.numOfPatternAttr = DEFAULTNUMPATTERNATTR;
        random = new Random();
    }


    /**
     * Constructor with a value for CardGenerator class.
     *
     * @param numOfPatterns: integer value representing the number of
     *                       pattern attributes that will be used to
     *                       generate different cards.
     */

    public CardGenerator(int numOfPatterns)
    {
        this.numOfPatternAttr = numOfPatterns;
        random = new Random();
    }


    /**
     * Generates a Card using random integer attribute.
     *
     * @return card: Card instance representing the new Card
     *               generated with a random attribute.
     *
     */

    public Card generateCard()
    {
        Card card = new Card(this.getRandValue(1, this.numOfPatternAttr +1));
        return card;
    }


    /**
     * Gets a random integer using random.nextInt method.
     *
     * @see java.util.Random
     *
     * @param low: integer value representing the lowest
     *             value to start generating. Inclusive.
     *
     * @param high: integer value representing the highest
     *              value to end generating. Exclusive.
     *
     * @return R: integer value representing the random
     *            integer generated by the random.nextInt
     *            method.
     *
     */

    private int getRandValue(int low, int high)
    {

        int R = random.nextInt(high-low) + low;
        return R;
    }


}