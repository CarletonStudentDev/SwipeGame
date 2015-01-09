package Model;

/**
 * Card contains an attribute that will used
 * by the Game to check if the Player sorted
 * the selected card correctly.
 *
 * Each Card instance must have a corresponding
 * Pile instance with the same attribute.
 *
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.1
 * @since 2014-11-28
 *
 */

public class Card
{

    /**
     * patternAttribute: integer instance representing the
     *                   pattern for the Card.
     *
     */

    private int patternAttribute;


    /**
     * Constructor for the Card class.
     *
     * @param patternAttribute: integer instance representing the
     *                          pattern for the Card.
     *
     */

    public Card(int patternAttribute)
    {
        this.patternAttribute = patternAttribute;
    }


    /**
     * Getter for the patternAttribute.
     *
     * @return patterAttribute: integer instance representing the
     *                          pattern for the Card.
     *
     */

    public int getPatternAttribute()
    {
        return this.patternAttribute;
    }




}
