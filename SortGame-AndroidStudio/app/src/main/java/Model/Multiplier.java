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
 * @version 1.1
 * @since 2014-11-27
 *
 */

public class Multiplier implements Listener
{

    /**
     * MAXIMUM: integer representing the number of correct matches
     *          needed to scale the multiplier.
     *
     * MAX_MULTIPLIER: integer representing the maximum number
     *                 of the multiplier
     *
     * MULTIPLIERSCALIER: integer representing the amount of the multiplier
     *                    scale once the meter is at threshold.
     *
     */

    private static int MAXIMUM = 5,
                       MAX_MULTIPLIER = 16,
                       MULTIPLIER_SCALIER = 2;


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
        this.currentMultiplier = 1;
        this.meterCount = 0;
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

    public void increment()
    {
        // increment the meter count
        meterCount++;

        // check if it is at threshold
        if(meterCount>= MAXIMUM)
        {
            //if the multiplier is not at maximum
            if(currentMultiplier < MAX_MULTIPLIER)
            {
                //scale the score multiplier and reset the meter.
                this.currentMultiplier *= MULTIPLIER_SCALIER;
                this.meterCount = 0;
            }
        }
    }


    /**
     * Clears the meter
     *
     */

    public void clear()
    {
        this.currentMultiplier = 1;
        this.meterCount = 0;
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


    /**
     * @see Model.Listener
     *
     */

    @Override
    public void roundsOver(GameEvent ge)
    { }

}
