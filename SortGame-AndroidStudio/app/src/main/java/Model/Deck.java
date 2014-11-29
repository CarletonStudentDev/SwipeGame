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
     *DECKSIZE: the size of single deck kept constant temporarily
     */

    private Stack<Card> deck;
    private static int DECKSIZE = 15; 


/**
    * Constructor for the Deck class.
    *
    */

    public Deck()
    {
        this.deck = new Stack<Card>();
    }
    
    public void makeDeck(int size){
    	if(this.deck.size()==0){
    		CardGenerator cardGen = new CardGenerator();
    		for(int i = 0; i<size;i++){
    			this.deck.push(cardGen.generateCard());
    		}
    		
    	}
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
    

    public Stack<Card> getDeck() {
 		return deck;
 	}
}
