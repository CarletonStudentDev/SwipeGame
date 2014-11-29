package Model;

/**
 * Pile contains an attribute that the Game
 * will check whenever a Card is swiped to
 * a Pile instance.
 *
 * This class extends Card.
 *
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.0
 * @since 2014-11-28
 *
 */

public class Pile extends Card
{

    /**
     * patternAttribute: integer instance representing the
     *                   pattern for the Card.
     *
     */

    private int patternAttribute;


    /**
     * Constructor for the Pile class.
     *
     * @param patternAttribute :integer instance representing the
     *                          pattern for the Pile.
     */

    public Pile(int patternAttribute)
    {
        super(patternAttribute);
        this.patternAttribute = patternAttribute;
    }


    /**
     * Checks if the Card and the Pile have the same attribute.
     *
     * @param card: Card instance representing the Card that has
     *              been swiped to a certain Pile.
     *
     * @return bool: boolean value representing if the pattern
     *               attributes of the Card and the Pile are
     *               equivalent.
     *
     */

    public boolean checkCard(Card card)
    {
        return this.patternAttribute == card.getPatternAttribute();
    }
}
