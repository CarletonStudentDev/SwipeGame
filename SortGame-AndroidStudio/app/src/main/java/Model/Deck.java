package Model;

import java.util.Stack;

/**
 * Deck class contains the deck of cards
 * that the Player will use to play the
 * game.
 *
 * This class implements Listener
 *
 * @author Jeton Sinoimeri
 * @author Varun Sriram
 * @version 1.0
 * @since 2014-11-28
 *
 */

public class Deck implements Listener
{

    /**
     * deck: an instance of Stack of Cards to represent
     *       the a deck of cards.
     *
     */

    private Stack<Card> deck;


   /**
    * Constructor for the Deck class.
    *
    */

    public Deck()
    {
        this.deck = new Stack<Card>();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void correctMatch(GameEvent ge)
    {

    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void incorrectMatch(GameEvent ge)
    {

    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void timeOut(GameEvent ge)
    {

    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void livesFinish(GameEvent ge)
    {

    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void roundsOver(GameEvent ge)
    {

    }
}
