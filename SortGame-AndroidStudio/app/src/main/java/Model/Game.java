package Model;

import java.util.Stack;

/**
 * Game contains all the rules and information
 * about playing the card game.
 *
 * @author Jeton Sinoimeri
 * @version 1.2
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

    private Card activeCard;



    /**
     * Constructor for the Game class
     *
     */

    public Game(Listener player, Listener multiplier, Listener deck)
    {
        this.player = player;
        this.multiplier = multiplier;
        this.deck = deck;
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


    public Card getActiveCard()
    {
        return activeCard;
    }

    /**
     * Notifies the Listeners when the Timer has reached
     * zero.
     *
     */

    public void timeOut()
    {
        GameEvent ge = new GameEvent(this);

        this.player.timeOut(ge);
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
        GameEvent ge = new GameEvent(this);

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
        GameEvent ge = new GameEvent(this);

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
        // check the pattern between deck.card and pile

        return false;
    }


    public void drawCard()
    {
        Stack<Card> gameDeck = ((Deck) this.deck).getDeck();
        this.activeCard = gameDeck.pop();
    }


    /**
     * Main game loop for the game.
     *
     */

    public void play()
    {
        // correctMatchFound;

        // main loop check if number of lives != 0

               // this.roundsOver();

               // round loop check if number of cards in deck != 0

                      // draw card

                      // set Timer

                      // correctMatchFound = false;


                     // while timer != 0 && !correctMatchFound
                         // if checkMatch()
                             //  correctMatch()
                             // set correctMatchfound to true


                         // else
                             // inCorrectMatch()





    }

}
