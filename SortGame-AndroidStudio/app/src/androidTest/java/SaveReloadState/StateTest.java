package SaveReloadState;

import android.app.Activity;

import junit.framework.TestCase;

import Model.Deck;
import Model.Game;
import Model.Multiplier;
import Model.Player;


/**
 * JUnit testing for State class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-28
 *
 */

public class StateTest extends TestCase
{

    /**
     * Tests the saveState and loadState methods of the State class.
     *
     */

    public void testSaveState()
    {
        Game game = new Game(new Player(), new Multiplier(), new Deck());

        State state = new State(new Activity());


        state.saveState(game);
        game = state.loadState();


        Player player = (Player) game.getPlayer();
        assertEquals(3, player.getLives());
        assertEquals(0, player.getCurrentScore());

        Multiplier multiplier = (Multiplier) game.getMultiplierMeter();
        assertEquals(1, multiplier.getMultiplier());

        Deck deck = (Deck) game.getDeck();
        assertEquals(0, deck.deckSize());



        player.setLives(1);
        player.setCurrentScore(100);

        multiplier = new Multiplier(12);
        deck = new Deck(20);

        game = new Game(player, multiplier, deck);



        state.saveState(game);
        game = state.loadState();


        player = (Player) game.getPlayer();
        assertEquals(1, player.getLives());
        assertEquals(100, player.getCurrentScore());

        multiplier = (Multiplier) game.getMultiplierMeter();
        assertEquals(12, multiplier.getMultiplier());

        deck = (Deck) game.getDeck();
        assertEquals(20, deck.deckSize());


    }

}