package Model;

import com.example.owner.gameproject.R;

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
        this.currentMultiplier = R.integer.DEFAULTMULTIPLIER;
        this.meterCount = R.integer.DEFAULTMETERCOUNT;
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
        if(meterCount >= R.integer.MAXIMUM)
        {
            //if the multiplier is not at maximum
            if(currentMultiplier < R.integer.MAX_MULTIPLIER)
            {
                //scale the score multiplier and reset the meter.
                this.currentMultiplier *= R.integer.MULTIPLIER_SCALIER;
                this.meterCount = R.integer.DEFAULTMETERCOUNT;
            }
        }
    }


    /**
     * Clears the meter
     *
     */

    private void clear()
    {
        this.currentMultiplier = R.integer.DEFAULTMULTIPLIER;
        this.meterCount = R.integer.DEFAULTMETERCOUNT;
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
