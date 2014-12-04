package Model;

import com.example.owner.gameproject.R;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Game contains all the rules and information
 * about playing the card game.
 *
 * @author Jeton Sinoimeri
 * @version 1.6
 * @since 2014-11-10
 *
 */

public class Game
{

    /**
     * deck: Listener reference representing the deck of cards
     *       required to play the game.
     *
     * player: Listener reference representing the user required
     *         to play the game.
     *
     * multiplier: Listener reference representing the score
     *             multiplier the player reference requires
     *             to update the score.
     *
     */

    private Listener deck,
                     player,
                     multiplier;


    /**
     * activeCard: Card instance representing the top
     *             card of the deck.
     *
     */

    private Card activeCard;


    /**
     * pileArray: an instance of ArrayList of Pile objects
     *            representing all the piles of the game.
     *
     */

    private ArrayList<Pile> pileArray;


    /**
     * timedOut: boolean value representing if the Timer
     *           triggered an interrupt indicating that
     *           the time ran out.
     *
     */

    private boolean timedOut;



    /**
     * Constructor for the Game class
     *
     * @param player: Listener reference representing the user required
     *                to play the game.
     *
     * @param multiplier: Listener reference representing the score
     *                    multiplier the player reference requires
     *                    to update the score.
     *
     * @param deck: Listener reference representing the deck of cards
     *              required to play the game.
     *
     */

    public Game(Listener player, Listener multiplier, Listener deck)
    {
        this.player = player;
        this.multiplier = multiplier;
        this.deck = deck;

        this.pileArray = new ArrayList<Pile>();
        this.timedOut = false;
    }


    /**
     * Getter for the Multiplier.
     *
     * @return multiplier: Listener reference representing the Multiplier
     *
     */

    public Listener getMultiplierMeter()
    {
        return this.multiplier;
    }


    /**
     * Getter for the Player.
     *
     * @return player: Listener reference representing the Player
     *
     */

    public Listener getPlayer()
    {
        return this.player;
    }


    /**
     * Getter for the Player.
     *
     * @return deck: Listener reference representing the Deck
     *
     */

    public Listener getDeck()
    {
        return this.deck;
    }


    /**
     * Getter for the active card.
     *
     * @return activeCard: Card instance representing the top
     *                     card of the deck.
     *
     */

    public Card getActiveCard()
    {
        return activeCard;
    }


    /**
     * Getter for the timed out variable.
     *
     * @return timedOut: boolean value representing if the Timer
     *                   triggered an interrupt indicating that
     *                   the time ran out.
     *
     */

    public boolean getTimedOut()
    {
        return this.timedOut;
    }


    /**
     * Mutator for the timed out variable.
     *
     * @param timedOut: boolean value representing if the Timer
     *                  triggered an interrupt indicating that
     *                  the time ran out.
     */

    public void setTimedOut(boolean timedOut)
    {
        this.timedOut = timedOut;
    }


    /**
     * Adds a pile to the game.
     *
     * @param pile: Pile instance representing a pile that will
     *              be used to check for correct matches
     *
     */

    public void addPile(Pile pile)
    {
        this.pileArray.add(pile);

    }


    /**
     * Notifies the Listeners when the Timer has reached
     * zero.
     *
     */

    public void timeOut()
    {
        GameEvent ge = new GameEvent(this);

        this.setTimedOut(true);

        this.player.timeOut(ge);
    }


    /**
     * Asks the deck to draw a card.
     *
     */

    public void drawCard()
    {
        Stack<Card> gameDeck = ((Deck) this.deck).getDeck();
        this.activeCard = gameDeck.pop();
    }


    /**
     * Notifies the Listeners when there is a correct
     * match.
     *
     */

    private void correctMatch()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.correctMatch(ge);
        this.player.correctMatch(ge);
        this.deck.correctMatch(ge);

    }


    /**
     * Notifies the Listeners when there is an
     * incorrect match.
     *
     */

    private void incorrectMatch()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.incorrectMatch(ge);
        this.player.incorrectMatch(ge);
        this.deck.incorrectMatch(ge);

    }


    /**
     * Notifies the Listeners when the number of
     * lives finish.
     *
     */

    private void livesFinish()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the listeners
        this.multiplier.livesFinish(ge);
        this.player.livesFinish(ge);
        this.deck.livesFinish(ge);

    }


    /**
     * Notifies the Deck that when the round is over.
     *
     */

    private void roundsOver()
    {
        // create GameEvent
        GameEvent ge = new GameEvent(this);

        // notify the deck listener
        Deck d = (Deck)this.deck;
        d.roundsOver(ge);
    }


    /**
     * Checks the pattern on the current selected card and the
     * pile it is currently over.
     *
     * @return bool: boolean representing if the pattern matches
     *               or not.
     *
     */

    private boolean checkMatch()
    {
        for(int i = 0; i < this.pileArray.size(); i++)
        {
            if(this.pileArray.get(i).checkCard(this.activeCard))
                return true;
        }

        return false;
    }


    /**
     * Main game loop of the game. Contains all the
     * game logic and the rules of how the game
     * be played.
     *
     */

    public void play()
    {
        // boolean value to indicate that a correct match has been found
        boolean correctMatchFound;

        // Timer instance representing the countdown timer
        Timer timer;

        Player p = (Player)this.player;
        Deck d = (Deck)this.deck;

        // main loop check if number of lives != 0
        while(p.getLives() != R.integer.ENDOFLIVES)
        {
            // create new deck
            this.roundsOver();

            // round loop check if number of cards in deck != 0
            while(d.deckSize() != R.integer.ENDOFDECK)
            {
                // draw card
                this.drawCard();

                // set boolean values to false
                this.setTimedOut(false);
                correctMatchFound = false;

                // set Timer
                timer = new Timer(R.integer.TIMEPERCARD, this);
                timer.start();

                // drawn card loop to check if not time out and no correct match found
                while (!this.getTimedOut() && !correctMatchFound)
                {
                    // check match if correct notify listeners of correct match
                    if (this.checkMatch())
                    {
                        correctMatchFound = true;
                        this.correctMatch();
                    }

                    // otherwise notify listeners of incorrect match
                    else
                        this.incorrectMatch();

                }

                // cancel the time
                timer.cancel();
            }

        }

        // notify the listeners that the Player's lives have finished
        this.livesFinish();

    }

}
