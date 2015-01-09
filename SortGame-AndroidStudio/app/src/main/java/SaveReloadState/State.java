package SaveReloadState;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.owner.gameproject.R;
import Model.*;


/**
 * Saves and loads the State of the Game during
 * pausing and resuming of the app.
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-28
 *
 */

public class State
{

    /**
     * sharedPreferences: SharedPreferences instance representing the preferences
     *                    which will be shared among the entire app.
     *
     */

    private SharedPreferences sharedPreferences;


    /**
     * editor: SharedPreferences.Editor instance representing the editor
     *         which is responsible for writing to the file the state of
     *         the game.
     *
     */

    private SharedPreferences.Editor editor;



    /**
     * Constructor for the State class.
     *
     * @param activity: android.app.Activity instance representing the
     *                  App Activity.
     *
     */

    public State(Activity activity)
    {
        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }


    /**
     * Saves the state of the Game.
     *
     * @param game: Game instance representing the Game which is
     *              being played.
     *
     */

    public void saveState(Game game)
    {
        Player player = (Player) game.getPlayer();
        Deck deck = (Deck) game.getDeck();
        Multiplier multiplier = (Multiplier) game.getMultiplierMeter();

        // write to the file
        this.editor.putInt(""+R.integer.PlayerLives, player.getLives());
        this.editor.putLong(""+R.integer.PlayerScore, player.getCurrentScore());
        this.editor.putInt(""+R.integer.MultiplierMeter, multiplier.getMultiplier());
        this.editor.putInt(""+R.integer.CurrentDeckSize, deck.deckSize());

        // commit the changes
        this.editor.commit();

    }


    /**
     * Loads the state of the Game.
     *
     * @return game: Game instance representing the Game which is
     *               going to be played.
     *
     */

    public Game loadState()
    {
        // create and set the player's values according to the data saved
        Player player = new Player();
        player.setLives(this.sharedPreferences.getInt("", R.integer.PlayerLives));
        player.setCurrentScore(this.sharedPreferences.getLong("", R.integer.PlayerScore));


        // create a new deck with the value indicated in the saved data
        Deck deck = new Deck(this.sharedPreferences.getInt("", R.integer.CurrentDeckSize));


        // create new multiplier with the value indicated in the saved data
        Multiplier multiplier =
                new Multiplier(this.sharedPreferences.getInt("", R.integer.MultiplierMeter));


        // create new instance of Game
        Game game = new Game(player, multiplier, deck);


        return game;
    }


}
