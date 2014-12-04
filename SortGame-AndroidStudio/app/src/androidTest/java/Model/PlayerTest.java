package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * JUnit test for Player class
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2014-11-28
 *
 */

public class PlayerTest
{
    Player player;

    @Before
    public void setUp()
    {
        this.player = new Player();

    }

    @After
    public void tearDown()
    {
        this.player = null;

    }

    @Test
    public void testGetLives()
    {
        assertEquals(3, this.player.getLives());

    }

    @Test
    public void testSetLives()
    {

    }

    @Test
    public void testGetScore()
    {

    }

    @Test
    public void testSetScore()
    {

    }

    @Test
    public void testCorrectMatch()
    {

    }

    @Test
    public void testIncorrectMatch()
    {

    }

    @Test
    public void testTimeOut()
    {

    }

    @Test
    public void testLivesFinish()
    {

    }

    @Test
    public void testRoundsOver()
    {

    }
}