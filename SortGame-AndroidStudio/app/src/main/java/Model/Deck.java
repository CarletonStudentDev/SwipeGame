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
 * @version 1.6
 * @since 2014-11-28
 *
 */

public class Deck implements Listener
{

    /**
     * DECKSIZE: integer value representing the default size of the Deck.
     *
     */

    private static final int DECKSIZE = 15;


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
     * Constructor for Deck class with size.
     *
     * @param size: integer value representing the size of the
     *              Deck.
     *
     */

    public Deck(int size)
    {
        this();
        this.makeDeck(size);
    }


    /**
     * Getter for the size of the Deck.
     *
     * @return size: integer value representing the size
     *               of the deck.
     *
     */

    public int deckSize()
    {
        return this.deck.size();
    }


    /**
     * Creates a new Deck given a size.
     *
     * @param size: integer value representing the size that
     *              the deck should be.
     *
     */

    private void makeDeck(int size)
    {
        if(this.deck.isEmpty() && size > 0)
        {
            CardGenerator cardGen = new CardGenerator();

            for(int i = 0; i < size; i++)
                this.deck.push(cardGen.generateCard());
        }
    }


    /**
     * Getter for the deck.
     *
     * @return deck: an instance of Stack of Cards to represent
     *               the a deck of cards.
     *
     */

    public Stack<Card> getDeck()
    {
        return deck;
    }


    /**
     * Game notifies the Deck when the round is over.
     *
     * @param ge: GameEvent object representing the event that
     *            occurred.
     *
     */

    public void roundsOver(GameEvent ge)
    {
        Game g = (Game) ge.getSource();
        g.drawCard();
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
        Game g = (Game) ge.getSource();
        g.drawCard();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void livesFinish(GameEvent ge)
    {
        this.deck.clear();
        this.makeDeck(DECKSIZE);
    }

}