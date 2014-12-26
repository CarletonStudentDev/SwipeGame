package Model;

/**
 * Multiplier class representing the score
 * multiplier for the game. If the user gets
 * a certain amount of correct match, it will
 * increment the multiplier value. Otherwise
 * reset it to zero.
 *
 * This class implements Listener.
 *
 * @author Varun Sriram
 * @version 1.3
 * @since 2014-11-27
 *
 */

public class Multiplier implements Listener
{

    private static final int MAXIMUM = 5,
                             MAX_MULTIPLIER = 16,
                             MULTIPLIER_SCALIER = 2,
                             DEFAULTMULTIPLIER = 1,
                             DEFAULTMETERCOUNT = 0;

    /**
     * currentMultiplier: integer representing the current
     *                    multiplier the meter will show.
     *
     * meterCount: integer representing the number of correct
     *             matches the player made.
     *
     */

    private int currentMultiplier,
                meterCount;


    /**
     * Constructor of the multiplier class.
     *
     */

    public Multiplier()
    {
        this.currentMultiplier = DEFAULTMULTIPLIER;
        this.meterCount = DEFAULTMETERCOUNT;
    }


    /**
     * Getter method for the current multiplier.
     *
     * @return integer representing the current score multiplier.
     *
     */

    public int getMultiplier()
    {
        return this.currentMultiplier;
    }


    /**
     * Increments the multiplier meter.
     *
     */

    private void increment()
    {
        // increment the meter count
        meterCount++;

        // check if it is at threshold
        if(meterCount >= MAXIMUM)
        {
            //if the multiplier is not at maximum
            if(currentMultiplier < MAX_MULTIPLIER)
            {
                //scale the score multiplier and reset the meter.
                this.currentMultiplier *= MULTIPLIER_SCALIER;
                this.meterCount = DEFAULTMETERCOUNT;
            }
        }
    }


    /**
     * Clears the meter
     *
     */

    private void clear()
    {
        this.currentMultiplier = DEFAULTMULTIPLIER;
        this.meterCount = DEFAULTMETERCOUNT;
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void correctMatch(GameEvent ge)
    {
        this.increment();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void incorrectMatch(GameEvent ge)
    {
        this.clear();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void timeOut(GameEvent ge)
    {
        this.clear();
    }


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void livesFinish(GameEvent ge)
    {
        this.clear();
    }

}
