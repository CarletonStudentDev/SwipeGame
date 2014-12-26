package Model;

import junit.framework.TestCase;

/**
 * JUnit testing for Timer class.
 *
 * @see junit.framework.TestCase
 *
 * @author Jeton Sinoimeri
 * @version 1.0
 * @since 2014-12-25
 *
 */

public class TimerTest extends TestCase
{

    /**
     * timer: Timer instance representing the CountDownTimer used
     *        in the game
     *
     */

    private Timer timer;


    /**
     * game: Game instance representing the game
     *
     */

    private Game game;




    /**
     * @see junit.framework.TestCase
     *
     * @throws Exception indicating that there was an exception
     *         in initializing of the fields and resources
     *
     */

    public void setUp() throws Exception
    {
        super.setUp();

        this.game = new Game(new Player(), new Multiplier(), new Deck());
        this.timer = new Timer(60000, this.game);
    }


    /**
     * @see junit.framework.TestCase
     *
     * @throws Exception indicating that there was an exception
     *         in deallocating of the fields and resources
     *
     */

    public void tearDown() throws Exception
    {
        this.timer = null;
        this.game = null;

    }


    /**
     * Tests the onFinish method of Timer class
     *
     */

    public void testOnFinish()
    {
        this.timer.onFinish();
        Player p = (Player)this.game.getPlayer();
        assertEquals(2, p.getLives());

    }


    /**
     * Tests the onTick method of the Timer class
     *
     */

    public void testOnTick()
    {
        for (int i = 700; i > 0; i--)
        {
            this.timer.onTick(i);

            assertEquals(i, this.timer.getTimeLeft());
        }

    }


    /**
     * Tests the getTimeLeft method of the Timer class
     *
     */

   public void testGetTimeLeft()
   {
       this.timer.onTick(23);
       assertEquals(23,this.timer.getTimeLeft());
   }
}